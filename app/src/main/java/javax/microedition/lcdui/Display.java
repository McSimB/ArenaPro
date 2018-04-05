/*
 * Copyright 2012 Kulikov Dmitriy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.microedition.lcdui;

import javax.microedition.lcdui.event.RunnableEvent;
import javax.microedition.midlet.MIDlet;
import javax.microedition.util.ContextHolder;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;

public class Display
{
	public static final int LIST_ELEMENT = 1;
	public static final int CHOICE_GROUP_ELEMENT = 2;
	public static final int ALERT = 3;
	
	public static final int COLOR_BACKGROUND = 0;
	public static final int COLOR_FOREGROUND = 1;
	public static final int COLOR_HIGHLIGHTED_BACKGROUND = 2;
	public static final int COLOR_HIGHLIGHTED_FOREGROUND = 3;
	public static final int COLOR_BORDER = 4;
	public static final int COLOR_HIGHLIGHTED_BORDER = 5;
	
	private static final int[] COLORS =
	{
		0xFFD0D0D0,
		0xFF000080,
		0xFF000080,
		0xFFFFFFFF,
		0xFFFFFFFF,
		0xFF000080
	};
	
	public static class ScreenActivity extends MicroActivity
	{
		public void onResume()
		{
			super.onResume();
			Display.getDisplay(null).changeActivity(this);
		}
	}
	
	public static class CanvasActivity extends MicroActivity
	{
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setFullScreenMode();
		}
		
		public void onResume()
		{
			super.onResume();
			Display.getDisplay(null).changeActivity(this);
		}
	}
	
	private static class AlertAdvancer implements CommandListener
	{
		public void commandAction(Command c, Displayable d)
		{
			Display.getDisplay(null).showCurrent();
		}
	}
	
	private static Display instance;
	
	private MIDlet context;
	private Displayable current;
	private MicroActivity activity;
	
	private static PowerManager powermanager;
	private static PowerManager.WakeLock wakelock;
	private static Vibrator vibrator;
	
	private AlertAdvancer advancer;
	private Overlay overlay;
	
	public static Display getDisplay(MIDlet midlet)
	{
		if(instance == null)
		{
			if(midlet == null)
			{
				if(ContextHolder.getContext() instanceof MIDlet)
				{
					midlet = (MIDlet)ContextHolder.getContext();
				}
				else
				{
					throw new IllegalStateException("could not find default MIDlet");
				}
			}
			
			instance = new Display(midlet);
		}
		
		return instance;
	}
	
	private Display(MIDlet midlet)
	{
		context = midlet;
		advancer = new AlertAdvancer();
	}
	
	public void setCurrent(Displayable disp)
	{
		changeCurrent(disp);
		showCurrent();
	}
	
	public void setCurrent(Alert alert, Displayable disp)
	{
		changeCurrent(disp);
		
		alert.showDialog(activity != null ? activity : context);
		
		if(alert.finiteTimeout())
		{
			alert.setCommandListener(advancer);
			(new Thread(alert)).start();
		}
	}
	
	public void setOverlay(Overlay overlay)
	{
		this.overlay = overlay;
	}
	
	private void changeCurrent(Displayable disp)
	{
		if(current instanceof Canvas)
		{
			((Canvas)current).setOverlay(null);
		}
		
		if(disp instanceof Canvas)
		{
			((Canvas)disp).setOverlay(overlay);
		}
		
		current = disp;
	}
	
	private void changeActivity(MicroActivity activity)
	{
		this.activity = activity;
		showCurrent();
	}
	
	private void showCurrent()
	{
		if(activity == null)
		{
			activity = ContextHolder.getCurrentActivity();
		}
		
		if(activity != null)
		{
			if(current instanceof Canvas)
			{
				if(activity instanceof CanvasActivity)
				{
					activity.setCurrent(current);
				}
				else
				{
					activity.startActivity(CanvasActivity.class);
				}
			}
			else
			{
				if(activity instanceof ScreenActivity)
				{
					activity.setCurrent(current);
				}
				else
				{
					activity.startActivity(ScreenActivity.class);
				}
			}
		}
		else
		{
			if(current instanceof Canvas)
			{
				context.startActivity(CanvasActivity.class);
			}
			else
			{
				context.startActivity(ScreenActivity.class);
			}
		}
	}
	
	public Displayable getCurrent()
	{
		return current;
	}
	
	public void callSerially(Runnable r)
	{
		if(current != null)
		{
			current.getEventQueue().postEvent(RunnableEvent.getInstance(r));
		}
		else
		{
			r.run();
		}
	}
	
	public static boolean flashBacklight(int duration)
	{
		try
		{
			if(powermanager == null)
			{
				powermanager = (PowerManager)ContextHolder.getContext().getSystemService(Context.POWER_SERVICE);
				wakelock = powermanager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Display.flashBacklight");
			}
			
			if(wakelock.isHeld())
			{
				wakelock.release();
			}
			
			if(duration > 0)
			{
				wakelock.acquire(duration);
			}
			else if(duration < 0)
			{
				wakelock.acquire();
			}
		
			return true;
		}
		catch(Throwable t)
		{
			return false;
		}
	}
	
	public static boolean vibrate(int duration)
	{
		try
		{
			if(vibrator == null)
			{
				vibrator = (Vibrator)ContextHolder.getContext().getSystemService(Context.VIBRATOR_SERVICE);
			}
			
			vibrator.vibrate(duration);
			
			return true;
		}
		catch(Throwable t)
		{
			return false;
		}
	}
	
	public void setCurrentItem(Item item)
	{
		if(item.hasOwnerForm())
		{
			setCurrent(item.getOwnerForm());
		}
	}
	
	public static int getBestImageHeight(int imageType)
	{
		return 0;
	}
	
	public static int getBestImageWidth(int imageType)
	{
		return 0;
	}
	
	public static int getBorderStyle(boolean highlighted)
	{
		return highlighted ? Graphics.SOLID : Graphics.DOTTED;
	}
	
	public static int getColor(int colorSpecifier)
	{
		return COLORS[colorSpecifier];
	}
	
	public static void setColor(int colorSpecifier, int color)
	{
		COLORS[colorSpecifier] = color;
	}
	
	public static boolean isColor()
	{
		return true;
	}
	
	public static int numAlphaLevels()
	{
		return 255;
	}
	
	public static int numColors()
	{
		return Integer.MAX_VALUE;
	}
}