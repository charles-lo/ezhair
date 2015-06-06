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

public class PageTest_rotate_animation extends Fragment {
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
		m_Img = (ImageView) rootView.findViewById(R.id.img01);
		m_Img.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		m_ImgWidth = 360;
		m_ImgHeight = 640;
		rotate();
		rootView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rotate(false);

			}
		});
		return rootView;
	}

	private void rotate() {
		rotate(true);
	}

	private void rotate(boolean init) {
		
		int disWidth = PageTest_rotate_animation.this.getResources().getDisplayMetrics().widthPixels;
		int offsetWidth = (disWidth - m_ImgWidth) / 2;
		if (init) {
			m_Img.scrollBy(-1 * offsetWidth, 0);
		}
		m_Img.setPivotX(offsetWidth + offsetWidth);
		m_Img.setPivotY(m_ImgHeight);

		m_Animator = ValueAnimator.ofFloat(0, m_Angel);
		m_Animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				m_Img.setRotation((Float) animation.getAnimatedValue());

			}
		});
		m_Animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				m_Animator = ValueAnimator.ofFloat(m_Angel, -0);
				m_Animator.addUpdateListener(new AnimatorUpdateListener() {

					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						m_Img.setRotation((Float) animation.getAnimatedValue());

					}
				});
				m_Animator.setDuration(m_Duration);
				m_Animator.start();

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
		m_Animator.setDuration(m_Duration);
		m_Animator.start();
	}
}