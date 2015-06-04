package com.ezhair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ezhair.PageProfile.ProfileArgs;
import com.ezhair.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;

import android.app.Fragment;
import android.graphics.Color;
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

public class PageResult extends Fragment {
	private View m_RootLayout;
	private View m_CardFace;
	private View m_CardBack;
	private boolean toogleGreen, toogleBlack = true;
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
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		m_RootLayout = rootView.findViewById(R.id.title);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		list.setPadding(0, 0, 0, 0);
		rootView.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.rounded_btn_black_left_selected);
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.result_title);
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

		class Item {
			String m_Name;
			String m_Store;
			int m_Gender;
			int m_Age;
			int m_Rate;
			String m_Region;
			boolean m_Discount;

			Item(String name, int gender, int age, String store, int rate, String region, boolean discount) {
				m_Name = name;
				m_Store = store;
				m_Gender = gender;
				m_Age = age;
				m_Rate = rate;
				m_Region = region;
				m_Discount = discount;
			}
		}

		SearchAdapter() {
			m_Data.add(new Item("張小愛", GENDER_FEMALE, 29, "日式威廉(忠孝店)", 4, "台北市內湖區", true));
			m_Data.add(new Item("王珊珊", GENDER_FEMALE, 25, "日式威廉(忠孝店)", 4, "台北市中正區", true));
			m_Data.add(new Item("鍾大鵰", GENDER_MALE, 40, "日式威廉(忠孝店)", 2, "台北市文山區", false));
			m_Data.add(new Item("Apple Lin", GENDER_FEMALE, 20, "日式威廉(石牌店)", 1, "台北市北投區", true));
			m_Data.add(new Item("陳小愛", GENDER_FEMALE, 28, "小林髮廊(忠孝店)", 1, "台北市信義區", false));
			m_Data.add(new Item("Amber", GENDER_FEMALE, 99, "曼都髮廊(忠孝店)", 4, "台北市文山區", true));
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
				holder.avatar = (SimpleDraweeView) convertView.findViewById(R.id.avatar);
				holder.basicinfo = (TextView) convertView.findViewById(R.id.basic_info);
				holder.store = (TextView) convertView.findViewById(R.id.store_content);
				holder.region = (TextView) convertView.findViewById(R.id.region_content);
				holder.discount = (TextView) convertView.findViewById(R.id.discount_content);
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_01));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_02));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_03));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_04));
				holder.rates.add((ImageView) convertView.findViewById(R.id.rate_05));

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.basicinfo.setText(String.format("%s  (%s / %d)", m_Data.get(position).m_Name,
					(m_Data.get(position).m_Gender == GENDER_MALE) ? "男" : "女", m_Data.get(position).m_Age));
			holder.store.setText(m_Data.get(position).m_Store);
			holder.region.setText(m_Data.get(position).m_Region);
			String uriBase = "http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_";
			DecimalFormat df = new DecimalFormat("'0'.jpg");
			final Uri uri = Uri.parse(uriBase + df.format(position + 20));
			//
			int width, height;
			width = height = (int) (PageResult.this.getActivity().getResources().getDisplayMetrics().density * 115);
			ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
					.setResizeOptions(new ResizeOptions(width, height))
					.setLocalThumbnailPreviewsEnabled(true)
					.setProgressiveRenderingEnabled(true).build();
			DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
					.setOldController(holder.avatar.getController()).build();
			holder.avatar.setController(controller);
			//
			holder.discount.setText(m_Data.get(position).m_Discount ? "有" : "無");
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Fragment profile = new PageProfile();
					Bundle bundle = new Bundle();
					bundle.putString(PageProfile.ARG, new Gson().toJson(new ProfileArgs(uri.toString(), String.format("%s  (%s / %d / %d)",
							m_Data.get(position).m_Name, (m_Data.get(position).m_Gender == GENDER_MALE) ? "男" : "女",
							m_Data.get(position).m_Age, m_Data.get(position).m_Age), m_Data.get(position).m_Rate, 73829, "染燙85折",
							m_Data.get(position).m_Store, m_Data.get(position).m_Region)));
					profile.setArguments(bundle);
					((MainActivity) getActivity()).replaceFragment(profile);

				}
			});

			for (int i = 0; i < m_Data.get(position).m_Rate; i++) {
				holder.rates.get(i).setColorFilter(Color.TRANSPARENT);
				holder.rates.get(i).setImageResource(R.drawable.icon_star_select);
			}

			if (position % 2 == 1) {
				convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.gray_01));
			} else {
				convertView.setBackgroundColor(getActivity().getResources().getColor(android.R.color.white));
			}

			return convertView;
		}

		class ViewHolder {
			SimpleDraweeView avatar;
			TextView basicinfo;
			int gender;
			int age;
			TextView store;
			TextView region;
			TextView discount;
			List<ImageView> rates = new ArrayList<ImageView>();
		}
	}
}