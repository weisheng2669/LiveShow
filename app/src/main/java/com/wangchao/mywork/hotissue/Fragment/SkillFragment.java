package com.wangchao.mywork.hotissue.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class SkillFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_fragment_layout,container,false);
        return view;
    }
}
