package com.ezhair;

import java.util.ArrayList;
import java.util.List;

import com.ezhair.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PageSearch extends Fragment {
    View rootLayout;// = (View) findViewById(R.id.main_activity_root);
    View m_CardFace;// = (View) findViewById(R.id.main_activity_card_face);
    View m_CardBack;// = (View) findViewById(R.id.main_activity_card_back);
    private int m_FirstVisibleItem;
    private boolean toogle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_search, container, false);
		rootLayout = rootView.findViewById(R.id.search_title);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		final View title = (LinearLayout) rootView.findViewById(R.id.black_bar);
		m_CardFace = rootView.findViewById(R.id.green_bar);
		m_CardBack = title;
		list.setAdapter(new SearchAdapter());
		list.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return false;
			}});
		list.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				m_FirstVisibleItem = firstVisibleItem;
				if(firstVisibleItem == 0&& m_CardFace.getVisibility()==View.GONE && toogle){
					toogle = false;
					flipCard(m_CardFace, m_CardBack);
				}else if(firstVisibleItem != 0 && m_CardBack.getVisibility()==View.GONE){
					flipCard(m_CardBack, m_CardFace);
					toogle = true;
				}
				
			}});
		return rootView;
	}
	
	private void flipCard(View cardFace, View cardBack)
	{
	 
	    FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);
	 
	    if (cardFace.getVisibility() == View.GONE)
	    {
	        flipAnimation.reverse();
	    }
	    rootLayout.startAnimation(flipAnimation);
	}

	class SearchAdapter extends BaseAdapter {

		private List<Item> m_Data = new ArrayList<Item>();
		static final private int TYPE_DESC = 0;
		static final private int TYPE_BTN = 1; 

		class Item {
			int m_Type;
			String m_Title;
			String m_Desc;
			int m_Value;

			Item(String title, String Desc) {
				m_Title = title;
				m_Desc = Desc;
			}

			Item(int type, String title, String Desc) {
				m_Type = type;
				m_Title = title;
				m_Desc = Desc;
			}
		}

		SearchAdapter() {
			m_Data.add(new Item("店家", "日式威廉"));
			m_Data.add(new Item("縣市", "日式威廉"));
			m_Data.add(new Item("區", "日式威廉"));
			m_Data.add(new Item("評價", "日式威廉"));
			m_Data.add(new Item("年資", "日式威廉"));
			m_Data.add(new Item("性別", "日式威廉"));
			m_Data.add(new Item("折扣", "日式威廉"));
			m_Data.add(new Item("名字關鍵字", "日式威廉"));
//			m_Data.add(new Item(TYPE_BTN,"", ""));
			m_Data.add(new Item(TYPE_BTN,"", ""));
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
				if (m_Data.get(position).m_Type == TYPE_DESC) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_search_01, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.desc = (TextView) convertView.findViewById(R.id.desc);
				} else {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_search_02, parent, false);
					holder.btn = convertView.findViewById(R.id.sumbit);
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			if (m_Data.get(position).m_Type == TYPE_DESC) {
				holder.category.setText(m_Data.get(position).m_Title);
				holder.desc.setText(m_Data.get(position).m_Desc);
			}
			if (m_Data.get(position).m_Type == TYPE_DESC) {
				if (position % 2 == 1) {
					convertView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
				} else {
					convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
				}
			}

			return convertView;
		}

		class ViewHolder {
			TextView category;
			TextView desc;
			View btn;
		}
	}
}