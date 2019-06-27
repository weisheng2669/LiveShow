package com.wangchao.mywork.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.wangchao.mywork.R;
import com.wangchao.mywork.fragment.HomeFragment;
import com.wangchao.mywork.fragment.NearByFragment;
import com.wangchao.mywork.utils.activityrelation.InitActivity;
import com.wangchao.mywork.utils.adapterrelation.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InitActivity {

    /* Data */
    private String className = "MainActivity";
    List<Fragment> res;

    /*View*/
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;

    /* View relation*/
    FragmentManager manager;
    MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        initData();
        initAdapter();
        setAdapter();
    }


    @Override
    public void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_nav_home);
        viewPager = findViewById(R.id.main_viewPager);
    }

    @Override
    public void initAdapter() {
       myFragmentPagerAdapter = new MyFragmentPagerAdapter(manager,res);
    }

    @Override
    public void initData() {
        HomeFragment homeFragment = new HomeFragment();
        NearByFragment nearByFragment = new NearByFragment();
        res = new ArrayList<>();
        res.add(homeFragment);
        res.add(nearByFragment);
        manager = getSupportFragmentManager();
    }


    @Override
    public void initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hot_issue:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nearby:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.take_pic:
                        break;
                    case R.id.focus:
                        break;
                    case R.id.message_take:
                        break;

                }
                return true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.hot_issue);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.nearby);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void setAdapter() {
        viewPager.setAdapter(myFragmentPagerAdapter);
    }


}
