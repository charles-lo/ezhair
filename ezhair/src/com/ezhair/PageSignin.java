package com.ezhair;

import com.ezhair.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;

public class PageSignin extends Fragment {
	static private final int MESSAGE_STATE_LAUNCH = 1000;
	static private final String TAG = PageSignin.class.getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_signin, container, false);
//		final View title = rootView.findViewById(R.id.title);
//		final View notification = rootView.findViewById(R.id.notification);
//		final View hot = rootView.findViewById(R.id.hot);
//		final View near = rootView.findViewById(R.id.near);
//		final View search = rootView.findViewById(R.id.search);
//		final View mail = rootView.findViewById(R.id.mail);
		

		float dp = this.getResources().getDisplayMetrics().density;
		Log.d(TAG, "" + dp);
		

		return rootView;
	}
}