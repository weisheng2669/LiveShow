package com.wangchao.mywork.viewModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class MyViewPager extends ViewPager {
    private ViewParent parent;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        parent = getParent();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(this.getCurrentItem() != getAdapter().getCount()-1){
                    parent.requestDisallowInterceptTouchEvent(true);
                }else{
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(this.getCurrentItem() != getAdapter().getCount()-1){
                    parent.requestDisallowInterceptTouchEvent(true);
                }else{
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(this.getCurrentItem() != getAdapter().getCount()-1){
                    parent.requestDisallowInterceptTouchEvent(true);
                }else{
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return true;
    }

}
