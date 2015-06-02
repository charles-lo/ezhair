package com.ezhair;

import com.ezhair.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageNear extends Fragment {

	private GoogleApiClient m_GoogleApiClient;
	private GoogleMap m_Map;
	private Location m_Location;
	private static final float ZOOM_LEVEL = 16.0f;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_near, container, false);
		rootView.findViewById(R.id.black_bar).setVisibility(View.VISIBLE);
		rootView.findViewById(R.id.btn).setVisibility(View.GONE);
		rootView.findViewById(R.id.bottom_region).setBackground(null);

		rootView.findViewById(R.id.right_tab).setBackgroundResource(R.drawable.rounded_btn_black_right_selected);
		((ImageView) rootView.findViewById(R.id.bottom_01)).setImageResource(R.drawable.tabbar_foorter_search_select);

		MapView mapView = (MapView) rootView.findViewById(R.id.map);

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
						m_Map.addMarker((new MarkerOptions().position(
								new LatLng(m_Location.getLatitude(), m_Location.getLongitude())).title("Jordan EZHair 工作室")
								.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map_location))));
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

}