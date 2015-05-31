package com.ezhair;

import com.ezhair.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PageNear extends Fragment {
	private View m_RootLayout;
	private View m_CardFace;
	private View m_CardBack;
	private boolean m_ToogleGreen, m_ToogleBlack = true;
	private GoogleApiClient m_GoogleApiClient;
	private GoogleMap m_Map;
	private GestureDetector m_GestureDetector;
	private Location m_Location;
	private static final float ZOOM_LEVEL = 16.0f;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_near, container, false);
		final View touch = rootView.findViewById(R.id.touch);
		m_RootLayout = rootView.findViewById(R.id.title);
		final Button available = (Button) rootView.findViewById(R.id.available);
		available.setVisibility(View.VISIBLE);
		available.setClickable(true);
		available.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				m_ToogleGreen = false;
				m_ToogleBlack = true;
				((MainActivity) getActivity()).replaceFragment(new PageResult());
				
			}});
		rootView.findViewById(R.id.back).setVisibility(View.GONE);
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);
		final Button mapAvailable = (Button) rootView.findViewById(R.id.available_map);
		mapAvailable.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				m_ToogleGreen = false;
				m_ToogleBlack = true;
				((MainActivity) getActivity()).replaceFragment(new PageResult());
				
			}});

		rootView.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.rounded_btn_black_left_selected);
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.near_search);
		m_CardFace = rootView.findViewById(R.id.green_bar);
		m_CardBack = rootView.findViewById(R.id.black_bar);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);

		MapView mapView = (MapView) rootView.findViewById(R.id.map);
		m_GestureDetector = new GestureDetector(PageNear.this.getActivity(), new GestureDetector.OnGestureListener() {

			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}

			@Override
			public void onShowPress(MotionEvent e) {

			}

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return false;
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {

			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				if (e1.getY() < e2.getY() && velocityY > 0 && !m_ToogleBlack) {
					m_ToogleGreen = false;
					flipCard(m_RootLayout, m_CardFace, m_CardBack);
					m_ToogleBlack = true;
					mapAvailable.setVisibility(View.GONE);
					return true;
				}

				if (e1.getY() > e2.getY() && velocityY < 0 && !m_ToogleGreen) {
					m_ToogleBlack = false;
					flipCard(m_RootLayout, m_CardBack, m_CardFace);
					m_ToogleGreen = true;
					mapAvailable.setVisibility(View.VISIBLE);
					return true;
				}
				return false;
			}
		});

		touch.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return m_GestureDetector.onTouchEvent(event);
			}
		});

		mapView.onCreate(null);
		mapView.onResume();
		m_Map = mapView.getMap();
		m_GoogleApiClient = new GoogleApiClient.Builder(PageNear.this.getActivity())
				.addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {

					@Override
					public void onConnected(Bundle connectionHint) {
						// TODO Auto-generated method stub
						Log.d("", "");
						m_Location = LocationServices.FusedLocationApi.getLastLocation(m_GoogleApiClient);
						m_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(
								new LatLng(m_Location.getLatitude(), m_Location.getLongitude()), ZOOM_LEVEL));
						m_Map.addMarker((new MarkerOptions().position(new LatLng(m_Location.getLatitude(), m_Location.getLongitude()))
								.title("Jordan EZHair 工作室")));
					}

					@Override
					public void onConnectionSuspended(int cause) {
						// TODO Auto-generated method stub
						Log.d("", "");
					}
				}).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {

					@Override
					public void onConnectionFailed(ConnectionResult result) {
						// TODO Auto-generated method stub
						Log.d("", "");
					}
				}).addApi(LocationServices.API).build();
		m_GoogleApiClient.connect();
		Location location = LocationServices.FusedLocationApi.getLastLocation(m_GoogleApiClient);
		if (location != null) {

			m_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),
					ZOOM_LEVEL));
			mapView.invalidate();
		}

		return rootView;
	}

	private void flipCard(View rootLayout, View cardFace, View cardBack) {

		FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

		if (cardFace.getVisibility() == View.GONE) {
			flipAnimation.reverse();
		}
		rootLayout.startAnimation(flipAnimation);
	}

}