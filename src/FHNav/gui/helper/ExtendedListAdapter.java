package FHNav.gui.helper;

/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;

import FHNav.gui.R;
import FHNav.model.Veranstaltung;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Demonstrates how to write an efficient list adapter. The adapter used in this
 * example binds to an ImageView and to a TextView for each row in the list.
 * 
 * To work efficiently the adapter implemented here uses two techniques: - It
 * reuses the convertView passed to getView() to avoid inflating View when it is
 * not necessary - It uses the ViewHolder pattern to avoid calling
 * findViewById() when it is not necessary
 * 
 * The ViewHolder pattern consists in storing a data structure in the tag of the
 * view returned by getView(). This data structures contains references to the
 * views we want to bind data to, thus avoiding calls to findViewById() every
 * time getView() is invoked.
 */

public class ExtendedListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	ArrayList<Veranstaltung> items;
	ArrayList<Boolean> checked;
	
	public ExtendedListAdapter(Context context, ArrayList<Veranstaltung> items) {

		mInflater = LayoutInflater.from(context);
		this.items = items;
		checked = new ArrayList<Boolean>();

		for(Veranstaltung v:items)
		{
			checked.add(false);
		}
	}
	

	/**
	 * Make a view to hold each row.
	 * 
	 * @see android.widget.ListAdapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		// When convertView is not null, we can reuse it directly, there is no
		// need
		// to reinflate it. We only inflate a new View when the convertView
		// supplied
		// by ListView is null.
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.editagendarow, null);

			holder = new ViewHolder();
			holder.bottomtext = (TextView) convertView.findViewById(R.id.extenden_row_bottomtext);
			holder.toptext = (TextView) convertView.findViewById(R.id.extenden_row_toptext);
			holder.checkbox = (CheckBox) convertView.findViewById(R.id.extenden_row_checkbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();		
		}
		
		final int pos = position;
		Veranstaltung ve = items.get(position);
		// Bind the data efficiently with the holder.
		holder.bottomtext.setText(ve.getRaum());
		holder.toptext.setText(ve.getName());
		holder.checkbox.setChecked(checked.get(position));
		//F�r Click auf Box
		holder.checkbox.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.e("", "klickBox");
				boolean tmp = checked.get(pos);
				checked.set(pos, !tmp);
				holder.checkbox.setChecked(!tmp);
			}
		});
		//F�r Click in Zeile
		convertView.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Log.e("", "klick");
				boolean tmp = checked.get(pos);
				checked.set(pos, !tmp);
				holder.checkbox.setChecked(!tmp);
			}
		});

		return convertView;
	}
	/**
	 * The number of items in the list is determined by the number of speeches
	 * in our array.
	 * 
	 * @see android.widget.ListAdapter#getCount()
	 */
	public int getCount() {
		return items.size();
	}

	/**
	 * Since the data comes from an array, just returning the index is sufficent
	 * to get at the data. If we were using a more complex data structure, we
	 * would return whatever object represents one row in the list.
	 * 
	 * @see android.widget.ListAdapter#getItem(int)
	 */
	public Object getItem(int position) {
		return position;
	}

	/**
	 * Use the array index as a unique id.
	 * 
	 * @see android.widget.ListAdapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return position;
	}



	public ArrayList<Veranstaltung> getItems() {
		return items;
	}


	public void setItems(ArrayList<Veranstaltung> items) {
		this.items = items;
	}


	public ArrayList<Boolean> getChecked() {
		return checked;
	}


	public void setChecked(ArrayList<Boolean> checked) {
		this.checked = checked;
	}



	static class ViewHolder {
		TextView bottomtext;
		TextView toptext;
		CheckBox checkbox;
	}
}