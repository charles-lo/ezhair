package com.ezhair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ezhair.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;

import android.app.Dialog;
import android.app.Fragment;
import android.app.Service;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PageMessage extends Fragment {
	
	public static final String ARG = "profile_arg";
	private List<Item> m_Data = new ArrayList<Item>();
	private ListView m_List;
	private View m_Empty;
	
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

	static public class MessageArgs {
		private String m_Avatar;

		MessageArgs(String avatar) {
			m_Avatar = avatar;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		MessageArgs args = new Gson().fromJson((String) getArguments().getString(ARG), MessageArgs.class);
		m_Data.add(new Item("張小愛", "A", "2015/05/20"));
		m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
		m_Data.add(new Item("張小愛", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
		m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
		m_Data.add(new Item("張小愛", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));
		m_Data.add(new Item("王珊珊", "陳設計師人非常nice，技術也非常好。很推薦的設計師", "2015/05/20"));

		final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_message, container, false);
		final MessageAdapter adapter = new MessageAdapter();
		final View back = rootView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();

			}
		});
		final Button rightBtn = (Button) rootView.findViewById(R.id.rightBtn);
		rightBtn.setText(R.string.write);
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(PageMessage.this.getActivity());

				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.view_write);

				dialog.show();
				Button btn = (Button) dialog.findViewById(R.id.btn);
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						adapter.addItem(new Item("鍾屌屌", ((EditText) dialog.findViewById(R.id.edit)).getText().toString(), new SimpleDateFormat("yyyy/MM/dd").format(new Date())));
						((Vibrator) PageMessage.this.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(100);
						dialog.dismiss();
					}
				});
			}
		});

		final TextView title = (TextView) rootView.findViewById(R.id.page_title);
		title.setText(R.string.message);

		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		m_List = (ListView) rootView.findViewById(R.id.listView);
		m_List.setPadding(0, 0, 0, ((BitmapDrawable) getResources().getDrawable(R.drawable.tabbar_foorter_search)).getBitmap()
				.getHeight());
		m_List.setAdapter(adapter);
		m_Empty = rootView.findViewById(R.id.no_message);
		//
		int width, height;
		width = height = (int) (PageMessage.this.getActivity().getResources().getDisplayMetrics().density * 189.33);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(args.m_Avatar))
				.setResizeOptions(new ResizeOptions(width, height))
				.setLocalThumbnailPreviewsEnabled(true)
				.setProgressiveRenderingEnabled(true).build();
		SimpleDraweeView image = ((SimpleDraweeView) rootView.findViewById(R.id.avatar));
		DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
				.setOldController(image.getController()).build();
		image.setController(controller);
		check();
		return rootView;
	}
	
	void check(){
		if (m_Data.size() == 0){
			m_List.setVisibility(View.INVISIBLE);
			m_Empty.setVisibility(View.VISIBLE);
		} else {
			m_Empty.setVisibility(View.INVISIBLE);
			m_List.setVisibility(View.VISIBLE);
		}
	}

	class MessageAdapter extends BaseAdapter {

		MessageAdapter() {
			
		}
		
		public void addItem(Item item){
			m_Data.add(0, item);
			notifyDataSetChanged();
			check();
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
				if (m_Data.indexOf(item) == position) {
					i.remove();
					break;
				}
			}
			notifyDataSetChanged();
			if(m_Data.size() == 0){
				check();
			}
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
			if (m_Data.get(position).m_LongPress) {
				edit.setVisibility(View.VISIBLE);
				delete.setVisibility(View.VISIBLE);
			} else {
				edit.setVisibility(View.INVISIBLE);
				delete.setVisibility(View.INVISIBLE);
			}
			holder.m_Name.setText(m_Data.get(position).m_Name);
			holder.m_Content.setText(m_Data.get(position).m_Content);
			holder.m_Date.setText(m_Data.get(position).m_Date);

			edit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					final Dialog dialog = new Dialog(PageMessage.this.getActivity());

					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.view_write);
					((EditText) dialog.findViewById(R.id.edit)).setText(m_Data.get(position).m_Content);

					dialog.show();
					Button btn = (Button) dialog.findViewById(R.id.btn);
					btn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							((EditText) dialog.findViewById(R.id.edit)).getText();
							m_Data.get(position).m_Content = ((EditText) dialog.findViewById(R.id.edit)).getText().toString();
							m_Data.get(position).m_Date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
							((Vibrator) PageMessage.this.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(100);
							m_Data.get(position).m_LongPress = false;
							edit.setVisibility(View.INVISIBLE);
							delete.setVisibility(View.INVISIBLE);
							dialog.dismiss();
						}
					});
				}
			});
			
			delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {					
					((Vibrator) PageMessage.this.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(100);
					delete(position);
				}
			});
			convertView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					if (m_Data.get(position).m_LongPress) {
						((Vibrator) PageMessage.this.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(100);
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