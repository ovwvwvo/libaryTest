package com.ovwvwvo.jlibarytest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.ovwvwvo.jlibarytest.R;
import com.ovwvwvo.jlibrary.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 16/8/26
 */
public class BrushDrawing extends View {

    private Bitmap mBitmapBrush;
    private Vector2 mBitmapBrushDimensions;

    VelocityTracker mVelocityTracker;

    static PointF lastPointF = new PointF();

    private List<Vector2> mPositions = new ArrayList<>(100);

    private static class Vector2 {

        public Vector2(float x, float y, float angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float x;
        public float y;
        public float angle;

    }

    public BrushDrawing(Context context) {
        super(context);
        init(context);
    }

    public BrushDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BrushDrawing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BrushDrawing(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mBitmapBrush = BitmapFactory.decodeResource(context.getResources(), R.drawable.texshape_brush6);
        mBitmapBrushDimensions = new Vector2(mBitmapBrush.getWidth(), mBitmapBrush.getHeight());
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Vector2 pos : mPositions) {
            canvas.save();
            canvas.drawBitmap(mBitmapBrush, pos.x, pos.y, null);
            canvas.restore();

        }
    }

    int i;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mVelocityTracker.addMovement(event);
        mVelocityTracker.computeCurrentVelocity(10);
        float xVelocity = mVelocityTracker.getXVelocity();
        float vx = mVelocityTracker.getXVelocity();
        LogUtil.i("getXVelocity" + vx);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastPointF.set(0, 0);
                i = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float dx = x - lastPointF.x, dy = y - lastPointF.y;
                LogUtil.i("" + i);
                if (dx * dx + dy * dy > 100) {
                    LogUtil.i("hahhah" + i);
                    mPositions.add(new Vector2(x - mBitmapBrushDimensions.x / 2,
                            y - mBitmapBrushDimensions.y / 2));
                    lastPointF.set(x, y);
                    invalidate();
                }
                i++;
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.clear();
                break;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mVelocityTracker != null)
            mVelocityTracker.recycle(); //一般在onDetachedFromWindow中调用
    }
}
