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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PageSearch extends Fragment {
	private View m_RootLayout;
	private View m_CardFace;
	private View m_CardBack;
	private boolean toogleGreen, toogleBlack = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_common, container, false);
		final View bottom = rootView.findViewById(R.id.bottom);
		m_RootLayout = rootView.findViewById(R.id.title);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		rootView.findViewById(R.id.center_tab).setBackgroundResource(R.drawable.rounded_btn_black_center_selected);
		((TextView)rootView.findViewById(R.id.page_title)).setText(R.string.page_search_title);
		m_CardFace = rootView.findViewById(R.id.green_bar);
		m_CardBack = rootView.findViewById(R.id.black_bar);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);
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
		static final private int TYPE_DESC = 0;
		static final private int TYPE_BTN = 1;
		static final private int TYPE_EDIT = 2;
		static final private int TYPE_RATE = 3;
		static final private int TYPE_SPACE = 4;

		class Item {
			int m_Type;
			String m_Title;
			String m_Desc;
			int m_Value;
			int m_Color;

			Item(String title, String Desc) {
				m_Title = title;
				m_Desc = Desc;
			}

			Item(String title, String Desc, int Color) {
				m_Title = title;
				m_Desc = Desc;
				m_Color = Color;
			}

			Item(int type, String title, String Desc) {
				m_Type = type;
				m_Title = title;
				m_Desc = Desc;
			}

			Item(int type, String title, int value) {
				m_Type = type;
				m_Title = title;
				m_Value = value;
			}
		}

		SearchAdapter() {
			m_Data.add(new Item(TYPE_EDIT, "店家", ""));
			m_Data.add(new Item("縣市", "台北市"));
			m_Data.add(new Item("區", "士林區"));
			m_Data.add(new Item(TYPE_RATE, "評價", 5));
			m_Data.add(new Item("年資", "2年"));
			m_Data.add(new Item("性別", "不拘"));
			m_Data.add(new Item("折扣", "不拘", getActivity().getResources().getColor(R.color.btn_green)));
			m_Data.add(new Item(TYPE_EDIT, "名字關鍵字", ""));
			m_Data.add(new Item(TYPE_SPACE, "", ""));
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
			int type = m_Data.get(position).m_Type;
			if (convertView == null) {
				if (type == TYPE_DESC) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_text, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.desc = (TextView) convertView.findViewById(R.id.desc);
				} else if (type == TYPE_BTN) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_btn, parent, false);
					holder.btn = (TextView) convertView.findViewById(R.id.btn);
				} else if (type == TYPE_RATE) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_rate, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.btn = (TextView) convertView.findViewById(R.id.btn);
				} else if (type == TYPE_EDIT) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_edit, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.edit = (EditText) convertView.findViewById(R.id.edit);
				} else if (type == TYPE_SPACE) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_space, parent, false);
					convertView.setClickable(false);
					convertView.setOnTouchListener(new OnTouchListener(){

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							return true;
						}});
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
				if (holder.type_desc == null && type == TYPE_DESC) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_text, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.desc = (TextView) convertView.findViewById(R.id.desc);
					convertView.setTag(holder);
				} else if (holder.type_btn == null && type == TYPE_BTN) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_btn, parent, false);
					holder.btn = (TextView) convertView.findViewById(R.id.btn);
					convertView.setTag(holder);
				} else if (holder.type_rate == null && type == TYPE_RATE) {
					holder.category = (TextView) convertView.findViewById(R.id.category);
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_rate, parent, false);
					holder.btn = (TextView) convertView.findViewById(R.id.btn);
					convertView.setTag(holder);
				} else if (holder.type_edit == null && type == TYPE_EDIT) {
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_edit, parent, false);
					holder.category = (TextView) convertView.findViewById(R.id.category);
					holder.edit = (EditText) convertView.findViewById(R.id.edit);
					convertView.setTag(holder);
				}
			}

			if (m_Data.get(position).m_Type == TYPE_DESC) {
				holder.category.setText(m_Data.get(position).m_Title);
				holder.desc.setText(m_Data.get(position).m_Desc);
				int color = m_Data.get(position).m_Color;
				if (color != 0) {
					holder.category.setTextColor(color);
				}
			}

			if (m_Data.get(position).m_Type == TYPE_RATE) {
				holder.category.setText(m_Data.get(position).m_Title);
			}
			if (m_Data.get(position).m_Type == TYPE_BTN) {
				holder.btn.setText(R.string.page_search_btn);
			}
			if (m_Data.get(position).m_Type == TYPE_EDIT) {
				holder.category.setText(m_Data.get(position).m_Title);
				holder.edit.setText(m_Data.get(position).m_Desc);
			}
			if (m_Data.get(position).m_Type == TYPE_DESC || m_Data.get(position).m_Type == TYPE_EDIT) {
				if (position % 2 == 1) {
					convertView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
				} else {
					convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
				}
			}

			return convertView;
		}

		class ViewHolder {
			View type_desc;
			View type_btn;
			View type_rate;
			View type_edit;
			TextView category;
			TextView desc;
			TextView btn;
			EditText edit;
		}
	}
}