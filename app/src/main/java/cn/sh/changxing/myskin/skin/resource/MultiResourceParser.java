package cn.sh.changxing.myskin.skin.resource;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;

/**
 * Created by yuanyi on 17-9-16.
 */

public class MultiResourceParser implements ResourceParser {
    private Resources mBaseResources;
    private Resources mEffectiveResources;
    private ThemeInfo mThemeInfo;

    public MultiResourceParser(Resources resources, ThemeInfo themeInfo) {
        mBaseResources = mEffectiveResources = resources;
        mThemeInfo = themeInfo;
    }

    @Override
    public String getString(int resid) {
        return null;
    }

    @Override
    public Drawable getDrawable(int resid) {
        return null;
    }

    @Override
    public int getColor(int resid) {
        return 0;
    }

    @Override
    public void changeThemeInfo(ThemeInfo themeInfo) {
        if (mThemeInfo.getThemePath() == null) {
            mEffectiveResources = mBaseResources;
        } else {

        }
    }
}
