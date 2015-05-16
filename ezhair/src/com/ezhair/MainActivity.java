package com.ezhair;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	FragmentTransaction ft = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fresco.initialize(MainActivity.this);
		setContentView(R.layout.activity_main);

		new PageHome();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PageHome()).commit();
		}
		// Uri uri =
		// Uri.parse("http://upload.wikimedia.org/wikipedia/commons/5/53/Zhuangzong_of_Later_Tang.jpg");
		// SimpleDraweeView draweeView = (SimpleDraweeView)
		// findViewById(R.id.my_image_view);
		// draweeView.setImageURI(uri);
		// draweeView.invalidate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	public void replaceFragment(Fragment newFragment) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, newFragment);
		ft.addToBackStack(null);
		ft.commit();
	}
}