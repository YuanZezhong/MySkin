package cn.sh.changxing.myskin.skin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.SimpleViewAttrManager;
import cn.sh.changxing.myskin.skin.ThemeInflaterFactory;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.ViewAttrManager;

/**
 * Created by YuanZezhong on 2017/9/19.
 *
 * 通过getLayoutInflater()获取的LayoutInflater使用的Factory为ThemeInflaterFactory,
 * 内部维护ViewAttrManager用于管理此Activity中对应主题更换的View
 */
public abstract class ThemeFragmentActivity extends FragmentActivity implements OnThemeChangedListener {
    private ThemeInflaterFactory mInflaterFactory;
    private ViewAttrManager mViewAttrManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeManager.getInstance().addOnThemeChangedListener(this);
        ViewAttrManager viewAttrManager = onCreateViewAttrManager();
        mViewAttrManager = viewAttrManager == null ? new SimpleViewAttrManager() : viewAttrManager;
        mInflaterFactory = new ThemeInflaterFactory(getLayoutInflater(), mViewAttrManager);
        getLayoutInflater().setFactory(mInflaterFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        mViewAttrManager.applyThemeChange();
    }

    /**
     * 创建ViewAttrItem管理器, 用来管理此Activity中所有对应主题更换的View
     *
     * @return null使用SimpleViewAttrManager
     */
    protected ViewAttrManager onCreateViewAttrManager() {
        return null;
    }

    public ThemeInflaterFactory getInflaterFactory() {
        return mInflaterFactory;
    }

    public ViewAttrManager getViewAttrManager() {
        return mViewAttrManager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.getInstance().removeOnThemeChangedListener(this);
        mViewAttrManager.clear();
        mViewAttrManager = null;
        mInflaterFactory = null;
    }
}

