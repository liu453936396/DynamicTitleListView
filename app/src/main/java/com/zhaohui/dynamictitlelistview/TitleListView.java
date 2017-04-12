package com.zhaohui.dynamictitlelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by E7240A on 2017-04-11.
 */

public class TitleListView extends ListView {

    private View mTitle;
    private volatile float Y;
    private volatile boolean direction = true;

    public TitleListView(Context context) {
        this(context, null);
    }

    public TitleListView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public TitleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //起始位置

                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动中坐标为
                        //判断上下移动
                        float changeY = event.getY();
                        if (changeY-Y>10) {
                            direction = true;
                        } else {
                            direction = false;
                        }
                        Y=changeY;

                        break;
                    case MotionEvent.ACTION_UP:
                        //最后位置
                        direction = true;

                        break;
                }
                return false;

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mTitle != null) {
            measureChild(mTitle, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mTitle != null) {
            mTitle.layout(0, 0, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mTitle != null) {
            drawChild(canvas, mTitle, getDrawingTime());
        }
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mTitle = inflater.inflate(R.layout.title, this, false);
    }

    void moveTitle(String title) {
        View bottomChild = getChildAt(0);
        if (bottomChild != null) {
            int bottom = bottomChild.getBottom();
            int height = mTitle.getMeasuredHeight();
            int y = 0;
            if (bottom < height) {
                y = bottom - height;
            }
            if(direction){

                mTitle.layout(0, y, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight() + y);
            }else {
                mTitle.layout(0, y, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight() - y);
                if (title != null) {
                    TextView title_text = (TextView) mTitle.findViewById(R.id.title_text);
                    title_text.setText(title);
                }
            }
        }
    }

    void updateTitle(String title) {
        if (title != null) {
            TextView title_text = (TextView) mTitle.findViewById(R.id.title_text);
            title_text.setText(title);
        }
        mTitle.layout(0, 0, mTitle.getMeasuredWidth(), mTitle.getMeasuredHeight());
    }
}



