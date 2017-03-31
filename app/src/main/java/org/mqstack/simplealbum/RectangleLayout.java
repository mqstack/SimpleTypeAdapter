package org.mqstack.simplealbum;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by mq on 2017/3/30.
 */

public class RectangleLayout extends FrameLayout {

    private float rate = 1;

    public RectangleLayout(@NonNull Context context) {
        super(context);
    }

    public RectangleLayout(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleLayout(@NonNull Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RectangleLayout, defStyle, 0);
        rate = a.getInt(R.styleable.RectangleLayout_rate, 1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * rate);
        setMeasuredDimension(width, height);
    }
}
