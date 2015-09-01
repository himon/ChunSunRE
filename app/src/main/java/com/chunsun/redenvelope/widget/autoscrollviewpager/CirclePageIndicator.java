package com.chunsun.redenvelope.widget.autoscrollviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CirclePageIndicator extends LinearLayout {

    private Context context;
    private int pageCount;// 总页数
    private int pagePositon;// 当前页面位置
    private int defaultPic;// 默认图片id
    private int activePic;// 获得页面图片id

    public int getDefaultPic() {
        return defaultPic;
    }

    public void setDefaultPic(int defaultPic) {
        this.defaultPic = defaultPic;
    }

    public int getActivePic() {
        return activePic;
    }

    public void setActivePic(int activePic) {
        this.activePic = activePic;
    }

    public int getPagePositon() {
        return pagePositon;
    }

    public void setPagePositon(int pagePositon) {
        this.pagePositon = pagePositon;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
        addView();
    }

    public CirclePageIndicator(Context context) {
        super(context);
        this.context = context;
    }

    public CirclePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    private void addView() {

        setDefaultPic(android.R.drawable.presence_invisible);
        setActivePic(android.R.drawable.presence_online);

        for (int i = 0; i < getPageCount(); i++) {
            ImageView pointView = new ImageView(context);
            if (i == 0) {
                System.out.println("activepic");
                pointView.setBackgroundResource(getActivePic());
                setPagePositon(i);
            } else {
                System.out.println("defaultpic");
                pointView.setBackgroundResource(getDefaultPic());
            }
            addView(pointView);
        }
    }

    public void changePointView(int cur) {
        System.out.println("cur-->" + cur + "/point-->" + getPagePositon());
        View view = getChildAt(getPagePositon());
        View curView = getChildAt(cur);
        if (view != null && curView != null) {
            ImageView pointView = (ImageView) view;
            ImageView curPointView = (ImageView) curView;
            pointView.setBackgroundResource(getDefaultPic());
            curPointView.setBackgroundResource(getActivePic());
            setPagePositon(cur);
        }
    }
}
