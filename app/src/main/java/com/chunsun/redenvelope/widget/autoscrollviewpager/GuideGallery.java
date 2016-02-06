package com.chunsun.redenvelope.widget.autoscrollviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class GuideGallery extends Gallery {
    /**
     * whether stop auto scroll when touching, default is true *
     */
    private boolean stopScrollWhenTouch = true;
    private boolean isAutoScroll = false;
    private boolean isStopByTouch = false;
    /**
     * auto scroll time in milliseconds, default is {@link #DEFAULT_INTERVAL} *
     */
    private long interval = 3000;
    private MyHandler handler;
    public static final int SCROLL_WHAT = 0;

    /**
     * item的数量
     */
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT:
                    scrollOnce();
                    sendScrollMessage(interval);
                default:
                    break;
            }
        }
    }

    public GuideGallery(Context context) {
        super(context);
        initVariable();
    }

    private void initVariable() {
        handler = new MyHandler();
    }

    public GuideGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVariable();
    }

    public GuideGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initVariable();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        int kEvent;
        if (isScrollingLeft(e1, e2)) { // Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else { // Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        if (this.getSelectedItemPosition() == 0)
            //this.setSelection(AdImageAdapter.size);
            this.setSelection(size);
        return true;

    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (stopScrollWhenTouch) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN && isAutoScroll) {
                isStopByTouch = true;
                stopAutoScroll();
            } else if (ev.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
                startAutoScroll();
            }
        }

        return super.onTouchEvent(ev);
    }

    /**
     * start auto scroll, first scroll delay time is {@link #getInterval()}
     */
    public void startAutoScroll() {
        isAutoScroll = true;
        sendScrollMessage(interval);
    }

    /**
     * stop auto scroll
     */
    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(SCROLL_WHAT);
    }

    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        handler.removeMessages(SCROLL_WHAT);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }

    /**
     * scroll only once
     */
    public void scrollOnce() {
        onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
    }

}
