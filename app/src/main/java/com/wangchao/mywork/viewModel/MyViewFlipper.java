package com.wangchao.mywork.viewModel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ViewFlipper;

public class MyViewFlipper extends ViewFlipper {

    private String className = "MyViewFlipper";
    ViewParent myParent;

    public MyViewFlipper(Context context) {
        super(context);

    }
    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        myParent = getParent();
        Log.i(className,myParent.getClass().getName());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myParent.requestDisallowInterceptTouchEvent(true);// 表示不拦截
                break;
            case MotionEvent.ACTION_MOVE:
                myParent.requestDisallowInterceptTouchEvent(true);// 表示不拦截
                break;
            case MotionEvent.ACTION_UP:
                myParent.requestDisallowInterceptTouchEvent(true);// 表示不拦截
                break;
            default:
                break;
        }
        return true;
    }

}
