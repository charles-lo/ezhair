package com.ezhair;

import com.ezhair.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PageHome extends Fragment {
	MainActivity m_Activity;
	static private final int MESSAGE_STATE_LAUNCH = 1000;
	static private final int MESSAGE_STATE_BTN1 = 1001;
	static private final int MESSAGE_STATE_BTN2 = 1002;
	static private final int MESSAGE_STATE_BTN3 = 1003;
	static private final int MESSAGE_STATE_BTN4 = 1004;
	ValueAnimator m_Animator;
	int m_delay;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.page_home, container, false);
		m_Activity = (MainActivity) getActivity();
		final View logo = rootView.findViewById(R.id.logo);
		final View title = rootView.findViewById(R.id.title);
		final View notification = rootView.findViewById(R.id.notification);
		final View hot = rootView.findViewById(R.id.hot);
		final View near = rootView.findViewById(R.id.near);
		final View search = rootView.findViewById(R.id.search);
		final View mail = rootView.findViewById(R.id.mail);

		logo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageSignin());

			}
		});
		
		hot.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageHot());

			}
		});

		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageSearch());

			}
		});
		
		near.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).replaceFragment(new PageNear());

			}
		});

		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MESSAGE_STATE_LAUNCH: {
					notification.setVisibility(View.VISIBLE);
					hot.setVisibility(View.VISIBLE);
					hot.setScaleX(0.0f);
					hot.setScaleY(0.0f);
					sendEmptyMessage(MESSAGE_STATE_BTN1);
					break;
				}
				case MESSAGE_STATE_BTN1: {
					m_Animator = ValueAnimator.ofFloat(0f, 1f);
					m_Animator.addUpdateListener(new AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							float value = (Float) animation.getAnimatedValue();
							hot.setScaleX(value);
							hot.setScaleY(value);
						}
					});
					m_Animator.addListener(new AnimatorListener() {

						@Override
						public void onAnimationStart(Animator animation) {

						}

						@Override
						public void onAnimationEnd(Animator animation) {
							near.setVisibility(View.VISIBLE);
							near.setScaleX(0.0f);
							near.setScaleY(0.0f);
							sendEmptyMessage(MESSAGE_STATE_BTN2);
						}

						@Override
						public void onAnimationCancel(Animator animation) {

						}

						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub

						}
					});
					m_Animator.setDuration(m_delay);
					m_Animator.start();
					break;
				}
				case MESSAGE_STATE_BTN2: {
					m_Animator = ValueAnimator.ofFloat(0f, 1f);
					m_Animator.addUpdateListener(new AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							float value = (Float) animation.getAnimatedValue();
							near.setScaleX(value);
							near.setScaleY(value);
						}
					});
					m_Animator.addListener(new AnimatorListener() {

						@Override
						public void onAnimationStart(Animator animation) {

						}

						@Override
						public void onAnimationEnd(Animator animation) {
							search.setVisibility(View.VISIBLE);
							search.setScaleX(0.0f);
							search.setScaleY(0.0f);
							sendEmptyMessage(MESSAGE_STATE_BTN3);
						}

						@Override
						public void onAnimationCancel(Animator animation) {

						}

						@Override
						public void onAnimationRepeat(Animator animation) {

						}
					});
					m_Animator.setDuration(m_delay);
					m_Animator.start();
					break;
				}
				case MESSAGE_STATE_BTN3: {
					m_Animator = ValueAnimator.ofFloat(0f, 1f);
					m_Animator.addUpdateListener(new AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							float value = (Float) animation.getAnimatedValue();
							search.setScaleX(value);
							search.setScaleY(value);
						}
					});
					m_Animator.addListener(new AnimatorListener() {

						@Override
						public void onAnimationStart(Animator animation) {

						}

						@Override
						public void onAnimationEnd(Animator animation) {
							mail.setVisibility(View.VISIBLE);
							mail.setScaleX(0.0f);
							mail.setScaleY(0.0f);
							sendEmptyMessage(MESSAGE_STATE_BTN4);
						}

						@Override
						public void onAnimationCancel(Animator animation) {

						}

						@Override
						public void onAnimationRepeat(Animator animation) {

						}
					});
					m_Animator.setDuration(m_delay);
					m_Animator.start();
					break;
				}
				case MESSAGE_STATE_BTN4: {
					m_Animator = ValueAnimator.ofFloat(0f, 1f);
					m_Animator.addUpdateListener(new AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							float value = (Float) animation.getAnimatedValue();
							mail.setScaleX(value);
							mail.setScaleY(value);
						}
					});
					m_Animator.addListener(new AnimatorListener() {

						@Override
						public void onAnimationStart(Animator animation) {

						}

						@Override
						public void onAnimationEnd(Animator animation) {

						}

						@Override
						public void onAnimationCancel(Animator animation) {

						}

						@Override
						public void onAnimationRepeat(Animator animation) {

						}
					});
					m_Animator.setDuration(m_delay);
					m_Animator.start();
					break;
				}
				}
				super.handleMessage(msg);
			}
		};
		int delayTime = 0;
		final int textPadding = title.getPaddingBottom();
		if (!m_Activity.isLaunched()) {
			delayTime = 5000;
			m_delay = 500;
			m_Activity.setLaunched(true);
		} else {
			m_delay = 0;
		}

		m_Activity.setLaunched(true);
		final int max = (int) (getResources().getDisplayMetrics().density * 190f);
		final int min = (int) (getResources().getDisplayMetrics().density * 72.67);
		m_Animator = ValueAnimator.ofInt(max, min);
		m_Animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int value = (Integer) animation.getAnimatedValue();
				logo.setPadding(0, value, 0, 0);
				title.setPadding(0, 0, 0, textPadding + max - value);
				title.setAlpha(1 - (((float) max - value) / (max - min)));
			}
		});
		m_Animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				handler.sendEmptyMessage(MESSAGE_STATE_LAUNCH);
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
		m_Animator.setDuration(delayTime);
		m_Animator.start();

		return rootView;
	}
}