package com.chunsun.redenvelope.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.chunsun.redenvelope.R;

/**
 * Created by Administrator on 2015/7/17.
 * 自定义Tab
 */
public class ChangeColorIconWithText extends View {

    private int mColor = 0xFF808080;
    private Bitmap mIconBitmap;
    private String mText = "";
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());


    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    /**
     * 图标的区域
     */
    private Rect mIconRect;
    /**
     * 文字的区域
     */
    private Rect mTextBound;
    /**
     * 文字的画笔
     */
    private Paint mTextPaint;

    public ChangeColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义属性的值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconWithText_tab_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_tab_color:
                    mColor = a.getColor(attr, 0xFF808080);
                    break;
                case R.styleable.ChangeColorIconWithText_tab_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorIconWithText_tab_text_size:
                    mTextSize = (int) a.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
            }
        }

        a.recycle();


        //初始化文字的范围
        mTextBound = new Rect();

        //初始化文字的画笔
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mColor);

        //通过Paint测量字的范围，这样mTextBound里面就包含了绘制当前Text所需要的left、right、top、bottom以及width和height
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //图标的边长
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());

        //绘制icon的位置
        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth / 2;

        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制图标
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

        //绘制原文本
        drawSourceText(canvas);

        super.onDraw(canvas);
    }

    /**
     * 绘制原文本
     *
     * @param canvas
     */
    private void drawSourceText(Canvas canvas) {

        mTextPaint.setColor(mColor);
        int x = mIconRect.left + mIconRect.width() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);

    }

    public void setmIcon(Bitmap bitmap, int color) {

        mIconBitmap = bitmap;
        mColor = color;
        invalidateView();
    }

    /**
     * 重绘
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_COLOR = "status_color";
    private static final String STATUS_BITMAP = "status_bitmap";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        //存储父View它所做的一些操作
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putInt(STATUS_COLOR, mColor);
        bundle.putParcelable(STATUS_BITMAP, mIconBitmap);
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mColor = bundle.getInt(STATUS_COLOR);
            mIconBitmap = bundle.getParcelable(STATUS_BITMAP);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }

        super.onRestoreInstanceState(state);
    }
}
