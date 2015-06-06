package com.ezhair;

import com.ezhair.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageTest_dimension extends Fragment {
	MainActivity m_Activity;
	final int m_Duration = 300;
	private ValueAnimator m_Animator;
	private ImageView m_Img;
	private float m_Angel = 20;
	private int m_ImgWidth;
	private int m_ImgHeight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_test_dimension, container, false);
		
		return rootView;
	}


}