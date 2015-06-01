package com.ezhair;

import java.util.ArrayList;
import java.util.List;

import com.ezhair.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import android.app.Fragment;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class PageProfile extends Fragment {

	public static final String ARG = "profile_arg";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_profile, container, false);

		rootView.findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();

			}
		});
		Button rightBtn = (Button) rootView.findViewById(R.id.rightBtn);
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setText(R.string.reserve);
		ProfileArgs args = new Gson().fromJson((String) getArguments().getString(ARG), ProfileArgs.class);
		((SimpleDraweeView) rootView.findViewById(R.id.avatar)).setImageURI(Uri.parse(args.m_Avatar));
		((TextView) rootView.findViewById(R.id.basic_info)).setText(args.m_Info);
		List<ImageView> rates = new ArrayList<ImageView>();
		rates.add((ImageView) rootView.findViewById(R.id.rate_01));
		rates.add((ImageView) rootView.findViewById(R.id.rate_02));
		rates.add((ImageView) rootView.findViewById(R.id.rate_03));
		rates.add((ImageView) rootView.findViewById(R.id.rate_04));
		rates.add((ImageView) rootView.findViewById(R.id.rate_05));
		for (int i = 0; i < args.m_Rate; i++) {
			rates.get(i).setColorFilter(Color.TRANSPARENT);
			rates.get(i).setImageResource(R.drawable.icon_star_select);
		}
		((TextView) rootView.findViewById(R.id.fame_content)).setText(String.valueOf(args.m_Fame));
		((TextView) rootView.findViewById(R.id.discount_content)).setText(args.m_Discount);
		((TextView) rootView.findViewById(R.id.store_content)).setText(args.m_Store);
		((TextView) rootView.findViewById(R.id.address_content)).setText(args.m_Address);

		return rootView;
	}

	static public class ProfileArgs {
		private String m_Avatar;
		private String m_Info;
		private int m_Rate, m_Fame;
		private String m_Discount;
		private String m_Store;
		private String m_Address;

		ProfileArgs(String avatar, String info, int rate, int fame, String discount, String store, String address) {
			m_Avatar = avatar;
			m_Info = info;
			m_Rate = rate;
			m_Fame = fame;
			m_Discount = discount;
			m_Store = store;
			m_Address = address;
		}
	}

}