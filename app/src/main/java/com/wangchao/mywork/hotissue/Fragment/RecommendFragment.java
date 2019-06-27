package com.wangchao.mywork.hotissue.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wangchao.mywork.R;
import com.wangchao.mywork.utils.adapterrelation.MyFragmentPagerAdapter;
import com.wangchao.mywork.utils.viewrelation.ViewAddUtils;
import com.wangchao.mywork.viewModel.MyViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {

    private String className = "HomeFragment";
    MyViewFlipper mViewFlipper;
    ViewAddUtils utils = new ViewAddUtils();
    DisplayMetrics dm;
    Context context;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_fragment_layout,container,false);
        initViews(view);
        return view;
    }

    public void initViews(View view) {
        dm = utils.getScreenDisPlayMetrics(context);
        initViewFlipper(view);
    }


    private void initViewFlipper(View view) {
        mViewFlipper = view.findViewById(R.id.my_view_flipper);
        RelativeLayout relativeLayoutOne = addViewForViewFlipper(R.drawable.viewflipper_one,0);
        RelativeLayout relativeLayoutTwo = addViewForViewFlipper(R.drawable.viewflipper_two,0);
        RelativeLayout relativeLayoutThree = addViewForViewFlipper(R.drawable.viewflipper_three,0);
        relativeLayoutOne.setTag(0);
        relativeLayoutTwo.setTag(1);
        relativeLayoutThree.setTag(2);
        mViewFlipper.addView(relativeLayoutOne);
        mViewFlipper.addView(relativeLayoutTwo);
        mViewFlipper.addView(relativeLayoutThree);

        mViewFlipper.setInAnimation(context, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(context, android.R.anim.fade_out);
        mViewFlipper.setFlipInterval(6000);
        //动画的监听
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时
                ViewGroup currentView = (ViewGroup) mViewFlipper.getCurrentView();
                addNavigation(currentView,Integer.valueOf(currentView.getTag().toString()));
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
                                addNavigation((ViewGroup) mViewFlipper.getCurrentView(),Integer.valueOf(mViewFlipper.getCurrentView().getTag().toString()));

                            }else{
                                mViewFlipper.showPrevious();
                                addNavigation((ViewGroup) mViewFlipper.getCurrentView(),Integer.valueOf(mViewFlipper.getCurrentView().getTag().toString()));
                            }
                        }
                        break;
                }
                return true;
            }


        });

    }
    private void addNavigation(ViewGroup currentView, int position) {
        //2.添加导航
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.bottomMargin = 20;
        params.rightMargin = 20;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.point_layout,null);
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
        RelativeLayout relativeLayout = new RelativeLayout(context);
        //1.添加Image
        RelativeLayout.LayoutParams image_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(utils.setImagesMatchParent(resId, context,0,0),image_params);
        if(position == 0){
            addNavigation(relativeLayout,0);
        }
        return relativeLayout;
    }




}
