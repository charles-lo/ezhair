package com.ezhair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ezhair.R;

import android.app.Fragment;
import android.app.Service;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PageMessage extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_common, container, false);
		final View back = rootView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();

			}
		});
		final Button rightBtn = (Button) rootView.findViewById(R.id.rightBtn);
		rightBtn.setText(R.string.whrite);
		rightBtn.setVisibility(View.VISIBLE);
		final TextView title = (TextView) rootView.findViewById(R.id.page_title);
		title.setText(R.string.message);
		
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		list.setPadding(0, 0, 0, ((BitmapDrawable)getResources().getDrawable(R.drawable.tabbar_foorter_search)).getBitmap().getHeight());
		list.setAdapter(new SearchAdapter());
		
		return rootView;
	}



	class SearchAdapter extends BaseAdapter {

		private List<Item> m_Data = new ArrayList<Item>();

		class Item {
			String m_Name;
			String m_Content;
			String m_Date;
			Boolean m_LongPress = false;

			Item(String name, String content, String date) {
				m_Name = name;
				m_Content = content;
				m_Date = date;
			}
		}

		SearchAdapter() {
			m_Data.add(new Item("張小愛", "A", "2015/05/20"));
			m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
			m_Data.add(new Item("張小愛", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
			m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
			m_Data.add(new Item("張小愛", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
			m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return m_Data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		void delete(int position) {
			Iterator<Item> i = m_Data.iterator();
			while (i.hasNext()) {
				Item item = i.next();
				if(m_Data.indexOf(item)==position){
					i.remove();
					break;
				}
			}
			notifyDataSetChanged();
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_message, parent, false);
				holder.m_Name = (TextView) convertView.findViewById(R.id.name);
				holder.m_Content = (TextView) convertView.findViewById(R.id.content);
				holder.m_Date = (TextView) convertView.findViewById(R.id.date);
				holder.m_Edit = (ImageView) convertView.findViewById(R.id.edit);
				holder.m_Delete = (ImageView) convertView.findViewById(R.id.delete);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			final ImageView edit = holder.m_Edit;
			final ImageView delete = holder.m_Delete;
			if(m_Data.get(position).m_LongPress){
				edit.setVisibility(View.VISIBLE);
				delete.setVisibility(View.VISIBLE);
			} else {
				edit.setVisibility(View.INVISIBLE);
				delete.setVisibility(View.INVISIBLE);
			}
			holder.m_Name.setText(m_Data.get(position).m_Name);
			holder.m_Content.setText(m_Data.get(position).m_Content);
			holder.m_Date.setText(m_Data.get(position).m_Date);
			
			delete.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					delete(position);
					
				}});
			convertView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					if (m_Data.get(position).m_LongPress) {
						m_Data.get(position).m_LongPress = false;
						edit.setVisibility(View.INVISIBLE);
						delete.setVisibility(View.INVISIBLE);
					} else {
						((Vibrator) PageMessage.this.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(100);
						m_Data.get(position).m_LongPress = true;
						edit.setVisibility(View.VISIBLE);
						edit.setAlpha(0.0f);
						edit.animate().alpha(1.0f).setDuration(500);
						delete.setVisibility(View.VISIBLE);
						delete.setAlpha(0.0f);
						delete.animate().alpha(1.0f).setDuration(500);
					}
					return false;
				}
			});
	
			
			return convertView;
		}

		class ViewHolder {
			TextView m_Name;
			TextView m_Content;
			TextView m_Date;
			ImageView m_Edit;
			ImageView m_Delete;
		}
	}
}