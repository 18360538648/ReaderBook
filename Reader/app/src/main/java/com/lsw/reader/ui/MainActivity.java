package com.lsw.reader.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lsw.reader.R;
import com.lsw.reader.ui.fragment.CommunityFragment;
import com.lsw.reader.ui.fragment.FindFragment;
import com.lsw.reader.ui.fragment.RecommendFragment;
import com.lsw.reader.view.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvp_indicator)
    RVPIndicator mRvpIndicator;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    // Fragment集合
    private List<Fragment> mTabContents;
    // Fragment适配器
    private FragmentPagerAdapter fragmentPagerAdapter;
    // Tab标签集合
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDatas();
        initViews();

    }

    /***
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas = Arrays.asList(getResources().getStringArray(R.array.tab_name));
        mTabContents=new ArrayList<>();
        mTabContents.add(new CommunityFragment());
        mTabContents.add(new FindFragment());
        mTabContents.add(new RecommendFragment());
    }

    /**
     * 初始化布局
     */
    private void initViews(){
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }

            @Override
            public int getCount() {
                return mTabContents.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        // 预加载页面个数，2个就可以在第一个页面，把另外两个页面预加载
        mViewPager.setOffscreenPageLimit(2);
        // 设置标题
        mRvpIndicator.setTabItemTitles(mDatas);
        mRvpIndicator.setViewPager(mViewPager,0);
    }
}
