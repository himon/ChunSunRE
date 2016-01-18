package com.chunsun.redenvelope.ui.base.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.widget.SwipeBackLayout;

/**
 * 想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager
 * 只需要调用SwipeBackLayout的setViewPager()方法即可
 *
 * @author xiaanming
 *
 */
public abstract class SwipeBackActivity<V, T extends BasePresenter<V>> extends MBaseActivity {
	protected SwipeBackLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
				R.layout.activity_swipeback_base, null);
		layout.attachToActivity(this);
	}
}
