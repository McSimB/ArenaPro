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

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Form extends Screen
{
	private ScrollView scrollview;
	private LinearLayout layout;
	
	private ArrayList<Item> items = new ArrayList();
	private ItemStateListener listener;
	
	public Form(String title)
	{
		setTitle(title);
	}
	
	public Form(String title, Item[] elements)
	{
		setTitle(title);
		items.addAll(Arrays.asList(elements));
	}
	
	public Item get(int index)
	{
		return items.get(index);
	}
	
	public int size()
	{
		return items.size();
	}
	
	public void append(String text)
	{
		append(new StringItem(null, text));
	}
	
	public void append(Image img)
	{
		append(new ImageItem(null, img, ImageItem.LAYOUT_DEFAULT, null));
	}
	
	public void append(Item item)
	{
		items.add(item);
		item.setOwnerForm(this);
		
		if(layout != null)
		{
			layout.addView(item.getItemView());
		}
	}
	
	public void insert(int index, Item item)
	{
		items.add(index, item);
		item.setOwnerForm(this);
		
		if(layout != null)
		{
			layout.addView(item.getItemView(), index);
		}
	}
	
	public void set(int index, Item item)
	{
		items.set(index, item).setOwnerForm(null);
		item.setOwnerForm(this);
		
		if(layout != null)
		{
			layout.removeViewAt(index);
			layout.addView(item.getItemView(), index);
		}
	}
	
	public void delete(int index)
	{
		items.remove(index).setOwnerForm(null);
		
		if(layout != null)
		{
			layout.removeViewAt(index);
		}
	}
	
	public void deleteAll()
	{
		for(Item item : items)
		{
			item.setOwnerForm(null);
		}
		
		items.clear();
		
		if(layout != null)
		{
			layout.removeAllViews();
		}
	}
	
	public void setItemStateListener(ItemStateListener listener)
	{
		this.listener = listener;
	}
	
	public void notifyItemStateChanged(Item item)
	{
		if(listener != null)
		{
			listener.itemStateChanged(item);
		}
	}
	
	public View getScreenView()
	{
		if(scrollview == null)
		{
			Context context = getParentActivity();
			
			layout = new LinearLayout(context);
			layout.setOrientation(LinearLayout.VERTICAL);
			
			scrollview = new ScrollView(context);
			scrollview.addView(layout);
			
			for(Item item : items)
			{
				layout.addView(item.getItemView());
			}
		}
		
		return scrollview;
	}
	
	public void clearScreenView()
	{
		scrollview = null;
		layout = null;
		
		for(Item item : items)
		{
			item.clearItemView();
		}
	}
	
	public boolean contextMenuItemSelected(MenuItem menuitem)
	{
		for(Item item : items)
		{
			if(item.contextMenuItemSelected(menuitem))
			{
				return true;
			}
		}
		
		return false;
	}
}