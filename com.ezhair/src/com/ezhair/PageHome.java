package com.ezhair;

import com.ezhair.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PageHome extends Fragment {
	static private final int MESSAGE_STATE_LAUNCH = 1000;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_home, container, false);
		final View title = rootView.findViewById(R.id.title);
		final View notification = rootView.findViewById(R.id.notification);
		final View hot = rootView.findViewById(R.id.hot);
		final View near = rootView.findViewById(R.id.near);
		final View search = rootView.findViewById(R.id.search);
		final View mail = rootView.findViewById(R.id.mail);

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MESSAGE_STATE_LAUNCH: {
					title.setVisibility(View.INVISIBLE);
					notification.setVisibility(View.VISIBLE);
					hot.setVisibility(View.VISIBLE);
					near.setVisibility(View.VISIBLE);
					search.setVisibility(View.VISIBLE);
					mail.setVisibility(View.VISIBLE);
					break;
				}
				}
				super.handleMessage(msg);
			}
		};
		handler.sendEmptyMessageDelayed(MESSAGE_STATE_LAUNCH, 1000);
		return rootView;
	}
}