package com.wangchao.mywork.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wangchao.mywork.R;
import com.wangchao.mywork.hotissue.Fragment.AnchorFragment;
import com.wangchao.mywork.hotissue.Fragment.FreshManFragment;
import com.wangchao.mywork.hotissue.Fragment.GameFragment;
import com.wangchao.mywork.hotissue.Fragment.RecommendFragment;
import com.wangchao.mywork.hotissue.Fragment.SkillFragment;
import com.wangchao.mywork.utils.adapterrelation.MyFragmentPagerAdapter;
import com.wangchao.mywork.utils.fragmentrelation.InitFragment;
import com.wangchao.mywork.utils.viewrelation.ViewAddUtils;
import com.wangchao.mywork.viewModel.MyViewPager;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements InitFragment {

    /* Data */
    private String className = "HomeFragment";
    ViewAddUtils utils = new ViewAddUtils();
    /* View */
    private TabLayout tabs_layout;
    private MyViewPager home_viewPager;

    /* View relation */
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
        View view = inflater.inflate(R.layout.home_fragment_layout,container,false);
        initViews(view);
        initData();
        return view;
    }
    private void addTabs() {
        //模拟从服务器拿到数据
        List<String> res_for_tabs = getListFromServer();
        for(String item:res_for_tabs){
            tabs_layout.addTab(tabs_layout.newTab().setText(item));
        }

    }
    private List<String> getListFromServer() {
        List<String> res = new ArrayList<>();
        res.add("推荐");
        res.add("交友");
        res.add("游戏");
        res.add("才艺");
        res.add("新人");
        res.add("主播");
        return res;
    }

    @Override
    public void initViews(View view) {
        home_viewPager = view.findViewById(R.id.home_viewpager);
        tabs_layout = view.findViewById(R.id.home_tabs);
    }

    @Override
    public void initData() {
        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> res = new ArrayList<>();
        RecommendFragment recommendFragment = new RecommendFragment();
        NearByFragment nearByFragment = new NearByFragment();
        GameFragment gameFragment = new GameFragment();
        SkillFragment skillFragment = new SkillFragment();
        FreshManFragment freshManFragment = new FreshManFragment();
        AnchorFragment anchorFragment = new AnchorFragment();
        res.add(recommendFragment);
        res.add(nearByFragment);
        res.add(gameFragment);
        res.add(skillFragment);
        res.add(freshManFragment);
        res.add(anchorFragment);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),res);
        home_viewPager.setAdapter(myFragmentPagerAdapter);
        tabs_layout.setupWithViewPager(home_viewPager);
        tabs_layout.removeAllTabs();
        addTabs();
    }
}
