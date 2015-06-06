package com.ezhair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ezhair.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PagePortfolio extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_common, container, false);
		final View bottom = rootView.findViewById(R.id.bottom);
		bottom.setBackground(null);
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
		right.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageReserve());
				
			}});
		
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		ListView list = (ListView) rootView.findViewById(R.id.listView);
		list.setPadding(0, 0, 0, 0);
		rootView.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.rounded_btn_black_left_selected);
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.portfolio);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);
		list.setAdapter(new PortfolioAdapter());
		list.setDivider(null);
		ViewGroup footer = new LinearLayout(PagePortfolio.this.getActivity());
		LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ((BitmapDrawable)getResources().getDrawable(R.drawable.tabbar_foorter_search)).getBitmap().getHeight());
		footer.setLayoutParams(lp);
		list.addFooterView(footer);
			
		return rootView;
	}


	class PortfolioAdapter extends BaseAdapter {

		private List<Uri> m_Data = new ArrayList<Uri>();
		String uriBase = "http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_";
		DecimalFormat df = new DecimalFormat("'0'.jpg");
		private int m_Width, m_Height;
		
		PortfolioAdapter() {
			for(int i=0; i<50; i++){
				m_Data.add(Uri.parse(uriBase + df.format(i + 20)));
			}
			m_Width = m_Height = (int) (PagePortfolio.this.getActivity().getResources().getDisplayMetrics().density * 115);
		}

		@Override
		public int getCount() {
			return m_Data.size() == 0 ? 0 : ((m_Data.size()-1)/3)+1;
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
			if (position == 0) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_portfolio01, parent, false);
				List<SimpleDraweeView> ats = new ArrayList<SimpleDraweeView>();
				ats.add((SimpleDraweeView) convertView.findViewById(R.id.avatar01));
				ats.add((SimpleDraweeView) convertView.findViewById(R.id.avatar02));
				ats.add((SimpleDraweeView) convertView.findViewById(R.id.avatar03));
				int index = 0;
				for (int i = 0; i < 3; i++) {
					index = position * 3 + i;
					if (index >= m_Data.size()) {
						break;
					}
					SimpleDraweeView image = ats.get(i);

					if (image != null) {
						ImageRequest request = ImageRequestBuilder.newBuilderWithSource(m_Data.get(index))
								.setResizeOptions(new ResizeOptions(m_Width, m_Height))
								.setLocalThumbnailPreviewsEnabled(true)
								.setProgressiveRenderingEnabled(true).build();
						DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
								.setOldController(image.getController()).build();
						image.setController(controller);
					}
				}
				return convertView;
			}
			
			
			ViewHolder holder = new ViewHolder();
			if (convertView == null || convertView.getTag() == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_portfolio, parent, false);
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.avatar01));
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.avatar02));
				holder.avatars.add((SimpleDraweeView) convertView.findViewById(R.id.avatar03));

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			int index = 0;
			for (int i = 0; i < 3; i++) {
				index = position * 3 + i;
				if (index >= m_Data.size()) {
					break;
				}
				SimpleDraweeView image = holder.avatars.get(i);

				if (image != null) {
					ImageRequest request = ImageRequestBuilder.newBuilderWithSource(m_Data.get(index))
							.setResizeOptions(new ResizeOptions(m_Width, m_Height))
							.setLocalThumbnailPreviewsEnabled(true)
							.setProgressiveRenderingEnabled(true).build();
					DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
							.setOldController(image.getController()).build();
					image.setController(controller);
				}
			}


			return convertView;
		}

		class ViewHolder {
			List<SimpleDraweeView> avatars = new ArrayList<SimpleDraweeView>();
		}
	}
}