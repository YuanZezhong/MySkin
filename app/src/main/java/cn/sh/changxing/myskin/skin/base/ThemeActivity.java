package cn.sh.changxing.myskin.skin.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.sh.changxing.myskin.skin.SimpleViewAttrManager;
import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInflaterFactory;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.ViewAttrManager;

/**
 * Created by YuanZezhong on 2017/9/19.
 */
public abstract class ThemeActivity extends Activity implements OnThemeChangedListener {
    protected ThemeInflaterFactory mInflaterFactory;
    protected ViewAttrManager mViewAttrManager;

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

    public ViewAttrManager onCreateViewAttrManager() {
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
        ThemeManager.getInstance().removeOnThemeChangedListener(this);
        mViewAttrManager.clear();
        mViewAttrManager = null;
        mInflaterFactory = null;
        super.onDestroy();
    }
}
