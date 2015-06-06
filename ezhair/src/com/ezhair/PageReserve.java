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
import android.app.FragmentManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
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

public class PageReserve extends Fragment {
	private ViewPager viewPager;//页卡内容  
    private ImageView imageView;// 动画图片  
    private TextView textView1,textView2,textView3;  
    private List<View> views;// Tab页面列表  
    private int offset = 0;// 动画图片偏移量  
    private int currIndex = 0;// 当前页卡编号  
    private int bmpW;// 动画图片宽度  
    private View view1,view2,view3;//各个页卡  

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_reserve, container, false);
		final View bottom = rootView.findViewById(R.id.bottom);
		bottom.setBackground(null);
		final View back = rootView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).back();

			}
		});
		InitViewPager(rootView);
		
		((TextView) rootView.findViewById(R.id.page_title)).setText(R.string.reserve);
//		
//		((TextView) rootView.findViewById(R.id.haircut_value)).setText("350");
//		((TextView) rootView.findViewById(R.id.wash_value)).setText("400");
//		((TextView) rootView.findViewById(R.id.dye_value)).setText("890");
//		((TextView) rootView.findViewById(R.id.perm_value)).setText("1200");
//		((TextView) rootView.findViewById(R.id.haircare_value)).setText("500");
			
		return rootView;
	}

	private void InitViewPager(View rootView) {  
		ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
		PagerTabStrip	tab = (PagerTabStrip) rootView.findViewById(R.id.pager_title_strip);
		tab.setTextSpacing(-1000);
//		tab.
		tab.setTabIndicatorColorResource(R.color.btn_green);
		
//		tab.set
        viewPager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager()));

    }  
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    	 final int PAGE_COUNT = 4;
    	    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3", "Tab4"  };
    	
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment();
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}