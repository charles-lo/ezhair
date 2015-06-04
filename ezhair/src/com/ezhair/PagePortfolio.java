package com.ezhair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ezhair.PageProfile.ProfileArgs;
import com.ezhair.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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

public class PagePortfolio extends Fragment {

	private static int GENDER_MALE, GENDER_FEMALE = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_common, container, false);
		final View bottom = rootView.findViewById(R.id.bottom);
		final View back = rootView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();

			}
		});
		final TextView right = (TextView) rootView.findViewById(R.id.rightBtn);
		right.setText(R.string.reserve);
		right.setVisibility(View.VISIBLE);
		
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		list.setPadding(0, 0, 0, ((BitmapDrawable)getResources().getDrawable(R.drawable.tabbar_foorter_search)).getBitmap().getHeight());
		rootView.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.rounded_btn_black_left_selected);
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.portfolio);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);
//		list.setAdapter(new PortfolioAdapter());
		
		return rootView;
	}


	class PortfolioAdapter extends BaseAdapter {

		private List<Uri> m_Data = new ArrayList<Uri>();
		String uriBase = "http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_";
		DecimalFormat df = new DecimalFormat("'0'.jpg");
		
		PortfolioAdapter() {
			for(int i=0; i<10; i++){
				
				m_Data.add(Uri.parse(uriBase + df.format(i + 20)));
			}
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_result, parent, false);
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.rate_01));
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.rate_02));
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.rate_03));

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

//			holder.avatars.setImageURI(uri);


			

			if (position % 2 == 1) {
				convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.gray_01));
			} else {
				convertView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
			}

			return convertView;
		}

		class ViewHolder {
			List<SimpleDraweeView> avatars = new ArrayList<SimpleDraweeView>();
		}
	}
}