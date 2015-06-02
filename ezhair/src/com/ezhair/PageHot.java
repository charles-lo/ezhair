package com.ezhair;

import java.util.ArrayList;
import java.util.List;

import com.ezhair.R;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PageHot extends Fragment {
	private View m_RootLayout;
	private View m_CardFace;
	private View m_CardBack;
	private boolean toogleGreen, toogleBlack = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_common, container, false);
		final View bottom = rootView.findViewById(R.id.bottom);
		final View btn = rootView.findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageResult());
				
			}});
		final View back = rootView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();
				
			}});
		m_RootLayout = rootView.findViewById(R.id.title);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		rootView.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.rounded_btn_black_left_selected);
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.hot_artist);
		m_CardFace = rootView.findViewById(R.id.green_bar);
		m_CardBack = rootView.findViewById(R.id.black_bar);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		list.setPadding(0, 0, 0, ((BitmapDrawable)getResources().getDrawable(R.drawable.tabbar_foorter_search)).getBitmap().getHeight());
		list.setAdapter(new SearchAdapter());
		list.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return false;
			}
		});
		list.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem == 0 && m_CardFace.getVisibility() == View.GONE && toogleGreen) {
					toogleGreen = false;
					flipCard(m_RootLayout, m_CardFace, m_CardBack);
					bottom.setVisibility(View.VISIBLE);
					toogleBlack = true;
				} else if (firstVisibleItem != 0 && m_CardBack.getVisibility() == View.GONE && toogleBlack) {
					toogleBlack = false;
					flipCard(m_RootLayout, m_CardBack, m_CardFace);
					bottom.setVisibility(View.GONE);
					toogleGreen = true;
				}

			}
		});
		return rootView;
	}

	private void flipCard(View rootLayout, View cardFace, View cardBack) {

		FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

		if (cardFace.getVisibility() == View.GONE) {
			flipAnimation.reverse();
		}
		rootLayout.startAnimation(flipAnimation);
	}

	class SearchAdapter extends BaseAdapter {

		private List<Item> m_Data = new ArrayList<Item>();

		class Item {
			String m_Name;
			String m_Store;
			int m_Value;
			String m_Address;

			Item(String name, String store, int value, String address) {
				m_Name = name;
				m_Store = store;
				m_Value = value;
				m_Address = address;
			}
		}

		SearchAdapter() {
			m_Data.add(new Item("張小愛", "日式威廉(忠孝店)", 1, "台北市中正區"));
			m_Data.add(new Item("王珊珊", "日式威廉(忠孝店)", 4, "台北市中正區"));
			m_Data.add(new Item("張小愛", "日式威廉(忠孝店)", 2, "台北市中正區"));
			m_Data.add(new Item("Apple Lin", "日式威廉(石牌店)", 1, "台北市北投區"));
			m_Data.add(new Item("陳小愛", "小林髮廊(忠孝店)", 1, "台北市中正區"));
			m_Data.add(new Item("Amber", "曼都髮廊(忠孝店)", 4, "台北市文山區"));
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_hot, parent, false);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.store = (TextView) convertView.findViewById(R.id.store);
				holder.address = (TextView) convertView.findViewById(R.id.address);
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_01));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_02));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_03));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_04));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_05));

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.name.setText(m_Data.get(position).m_Name);
			holder.store.setText(m_Data.get(position).m_Store);
			holder.address.setText(m_Data.get(position).m_Address);
			for(int i=0; i<m_Data.get(position).m_Value; i++){
				holder.rates.get(i).setColorFilter(Color.TRANSPARENT);
				holder.rates.get(i).setImageResource(R.drawable.icon_star_select);
			}

			if (position % 2 == 1) {
				convertView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
			} else {
				convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
			}

			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView store;
			TextView address;
			List<ImageView> rates = new ArrayList<ImageView>();
		}
	}
}