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

import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class Alert extends Screen implements Runnable, DialogInterface.OnClickListener
{
	public static final int FOREVER = -2;
	public static final Command DISMISS_COMMAND = new Command("", Command.OK, 0);
	
	private String text;
	private Image image;
	private AlertType type;
	private int timeout;
	
	private AlertDialog dialog;
	private Form form;
	
	private Command[] commands;
	private int positive, negative, neutral;
	
	public Alert(String title)
	{
		this(title, null, null, null);
	}
	
	public Alert(String title, String text, Image image, AlertType type)
	{
		setTitle(title);
		
		this.text = text;
		this.image = image;
		this.type = type;
	}
	
	public void setString(String str)
	{
		text = str;
	}
	
	public String getString()
	{
		return text;
	}
	
	public void setImage(Image img)
	{
		image = img;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}
	
	public int getTimeout()
	{
		return timeout;
	}
	
	public boolean finiteTimeout()
	{
		return timeout >= 0 && countCommands() < 2;
	}
	
	public void run()
	{
		if(finiteTimeout())
		{
			try
			{
				Thread.sleep(timeout);
				fireCommandAction(DISMISS_COMMAND, this);
			}
			catch(InterruptedException ie)
			{
			}
		}
	}
	
	public void showDialog(Context context)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle(getTitle());
		builder.setMessage(getString());
		builder.setIcon(new BitmapDrawable(getImage().getBitmap()));
		
		dialog = builder.create();
		
		commands = getCommands();
		Arrays.sort(commands);
		
		positive = -1;
		negative = -1;
		neutral = -1;
		
		int cmdtype;
		
		for(int i = 0; i < commands.length; i++)
		{
			cmdtype = commands[i].getCommandType();
			
			if(positive < 0 && cmdtype == Command.OK)
			{
				positive = i;
			}
			
			if(negative < 0 && cmdtype == Command.CANCEL)
			{
				negative = i;
			}
			
			if(neutral < 0)
			{
				neutral = i;
			}
		}
		
		if(positive >= 0)
		{
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, commands[positive].getLabel(), this);
		}
		
		if(negative >= 0)
		{
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, commands[negative].getLabel(), this);
		}
		
		if(neutral >= 0)
		{
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL, commands[neutral].getLabel(), this);
		}
		
		dialog.show();
	}
	
	public void dismissDialog()
	{
		if(dialog != null)
		{
			dialog.dismiss();
			dialog = null;
		}
	}

	public View getScreenView()
	{
		if(form == null)
		{
			form = new Form(getTitle());
			
			form.append(image);
			form.append(text);
		}
		
		return form.getDisplayableView();
	}

	public void clearScreenView()
	{
		form.clearDisplayableView();
		form = null;
	}

	public void onClick(DialogInterface dialog, int which)
	{
		switch(which)
		{
			case DialogInterface.BUTTON_POSITIVE:
				fireCommandAction(commands[positive], this);
				return;
				
			case DialogInterface.BUTTON_NEGATIVE:
				fireCommandAction(commands[negative], this);
				return;
				
			case DialogInterface.BUTTON_NEUTRAL:
				fireCommandAction(commands[neutral], this);
				return;
		}
	}
}