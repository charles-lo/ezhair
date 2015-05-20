package com.ezhair;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

public class EasyListView extends ListView {

	public interface OnOverScrollListener{
		void onUpOverscrolled();
		void onDownOverscrolled();
	}
	
	private	int	m_FirstVisibleItem;
	private	int	m_VisibleItemCount;
	private OnOverScrollListener m_OverScrollListener;
	
	public EasyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				m_FirstVisibleItem = firstVisibleItem;
				m_VisibleItemCount = visibleItemCount;
				if(m_OverScrollListener!=null && firstVisibleItem == 1){
					m_OverScrollListener.onDownOverscrolled();
				}
			}});
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
		if(clampedY && scrollY == 0){
			if(m_OverScrollListener!=null){
				if(m_FirstVisibleItem == 0){
					m_OverScrollListener.onUpOverscrolled();
				}else{
//					m_OverScrollListener.onDownOverscrolled();
				}
			}
		}
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}
	
	void setOnOverScrollListener(OnOverScrollListener listener){
		m_OverScrollListener = listener;
	}

}