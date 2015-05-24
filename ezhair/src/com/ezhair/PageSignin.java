package com.ezhair;

import com.ezhair.R;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageSignin extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_signin, container, false);
		 rootView.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageRegister());
			}
		});
		rootView.findViewById(R.id.forget).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageForget());
			}
		});
		
		TextView title = (TextView)rootView.findViewById(R.id.prefix);
		title.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Helvetica.ttf"));
		title = (TextView)rootView.findViewById(R.id.suffix);
		title.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Helvetica.ttf"));
		
		return rootView;
	}
}