package cn.sh.changxing.myskin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.fragment.TextFragment;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.ViewAttrManager;
import cn.sh.changxing.myskin.skin.base.ThemeFragmentActivity;

/**
 * Created by YuanZezhong on 2017/9/20.
 */
public class ThirdActivity extends ThemeFragmentActivity implements View.OnClickListener {
    private PagerAdapter mAdapter;
    private List<Fragment> mFragments;
    protected Button mTestBtn;
    protected ViewPager mViewPager;
    protected Button mRedBtn;
    protected Button mOriginalBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initViews();
        init();
    }

    private void initViews() {
        mTestBtn = ((Button) findViewById(R.id.btn_test));
        mRedBtn = ((Button) findViewById(R.id.btn_red));
        mOriginalBtn = ((Button) findViewById(R.id.btn_original));
        mViewPager = ((ViewPager) findViewById(R.id.vp_viewpager));
        mTestBtn.setOnClickListener(this);
        mRedBtn.setOnClickListener(this);
        mOriginalBtn.setOnClickListener(this);
    }

    private void init() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        mFragments = fragments;
        fragments.add(TextFragment.newInstance("First"));
        fragments.add(TextFragment.newInstance("Second"));
        fragments.add(TextFragment.newInstance("Third"));
        fragments.add(TextFragment.newInstance("Forth"));
        FragmentPagerAdapterImpl adapter = new FragmentPagerAdapterImpl(getSupportFragmentManager(), fragments);
        mAdapter = adapter;
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                ViewAttrManager manager = getViewAttrManager();
                break;
            case R.id.btn_red:
                ThemeManager.getInstance().changeTheme("红色主题");
                break;
            case R.id.btn_original:
                ThemeManager.getInstance().changeTheme(ThemeManager.THEME_NAME_DEFAULT);
                break;
        }
    }

    static class FragmentPagerAdapterImpl extends FragmentPagerAdapter {
        private List<Fragment> mFragments;

        public FragmentPagerAdapterImpl(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }
    }

    static class FragmentStatePagerAdapterImpl extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;

        public FragmentStatePagerAdapterImpl(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }
    }
}
