package com.ezhair;

import com.facebook.drawee.backends.pipeline.Fresco;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	FragmentTransaction ft = null;
	private boolean	launched = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fresco.initialize(MainActivity.this);
		setContentView(R.layout.activity_main);

		new PageHome();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PageHome()).commit();
		}
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

	@Override
	public void onBackPressed() {
		back();
	}
	
	public void back() {
		if (!getFragmentManager().popBackStackImmediate()) {
			finish();
		}
	}

	public void replaceFragment(Fragment newFragment) {
		getFragmentManager().beginTransaction().replace(R.id.container, newFragment).addToBackStack(null).commit();
	}
	
	/**
	 * @return the launched
	 */
	public boolean isLaunched() {
		return launched;
	}

	/**
	 * @param launched the launched to set
	 */
	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

}
