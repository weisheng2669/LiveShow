package com.wangchao.mywork;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.wangchao.mywork.utils.InitActivity;
import com.wangchao.mywork.utils.ViewAddUtils;

public class MainActivity extends AppCompatActivity implements InitActivity {
    private String className = "MainActivity";
    private final static int MIN_MOVE = 200;   //最小距离
    private static int count = 1;

    ViewFlipper mViewFlipper;
    ViewAddUtils utils = new ViewAddUtils();
    DisplayMetrics dm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    @Override
    public void initViews() {
        dm = utils.getScreenDisPlayMetrics(this);
        initViewFlipper();
    }

    private void initViewFlipper() {
        mViewFlipper = findViewById(R.id.my_view_flipper);

        RelativeLayout relativeLayoutOne = addViewForViewFlipper(R.drawable.viewflipper_one,0);
        RelativeLayout relativeLayoutTwo = addViewForViewFlipper(R.drawable.viewflipper_two,0);
        RelativeLayout relativeLayoutThree = addViewForViewFlipper(R.drawable.viewflipper_three,0);

        mViewFlipper.addView(relativeLayoutOne);
        mViewFlipper.addView(relativeLayoutTwo);
        mViewFlipper.addView(relativeLayoutThree);
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        mViewFlipper.setFlipInterval(3000);
        //动画的监听
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时
                Log.i(className,"onAnimationStart");
                ViewGroup currentView = (ViewGroup) mViewFlipper.getCurrentView();
                addNavigation(currentView,count);
                count = (count+1)%3;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //重复
            }
        });

        //开始轮播
        mViewFlipper.startFlipping();
        final int[] startX = {0};
        final int[] endX = { 0 };

        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(className,"Start = "+startX[0]);
                        startX[0] = (int) event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        endX[0] = (int) event.getX();
                        Log.i(className,"End = "+endX[0]);
                        if(Math.abs(endX[0] - startX[0])>100){
                            if(endX[0] > startX[0]){
                                mViewFlipper.showNext();
                                count = (count+1)%3;
                                addNavigation((ViewGroup) mViewFlipper.getCurrentView(),count);
                            }else{
                                mViewFlipper.showPrevious();
                                count = (count-1)%3;
                                addNavigation((ViewGroup) mViewFlipper.getCurrentView(),count);

                            }
                        }
                        break;
                }
                return true;
            }


        });
    }
    private void addNavigation(ViewGroup currentView, int position) {

        Log.i(className,"position = " + position);
        //2.添加导航
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.bottomMargin = 20;
        params.rightMargin = 20;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.point_layout,null);
        for(int i=0;i<linearLayout.getChildCount();i++){
            if(position == i) {
                ((ImageView) linearLayout.getChildAt(i)).setImageResource(R.drawable.selected_point);
            }else {
                ((ImageView) linearLayout.getChildAt(i)).setImageResource(R.drawable.unselected_point);
            }
        }
        currentView.addView(linearLayout,params);
    }

    private RelativeLayout addViewForViewFlipper(int resId,int position) {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        //1.添加Image
        RelativeLayout.LayoutParams image_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(utils.setImagesMatchParent(resId,MainActivity.this,0,0),image_params);

        if(position == 0){
            addNavigation(relativeLayout,0);
        }
        return relativeLayout;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getX() - e2.getX() > MIN_MOVE){
               mViewFlipper.showNext();
            }else if(e2.getX() - e1.getX() > MIN_MOVE){
               mViewFlipper.showPrevious();
            }
            return true;
        }
    }
}
