package cn.sh.changxing.myskin.skin.resource;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import cn.sh.changxing.myskin.skin.ThemeInfo;

/**
 * Created by yuanyi on 17-9-16.
 */

public class SingleResourceParser implements ResourceParser {
    private Resources mEffectiveResources;
    private ThemeInfo mTheme;

    public SingleResourceParser(Resources resources, ThemeInfo themeInfo) {
        mEffectiveResources = resources;
        mTheme = themeInfo;
    }

    @Override
    public String getString(int resid) {
        return mEffectiveResources.getString(getEffectiveId(resid));
    }

    @Override
    public Drawable getDrawable(int resid) {
        return mEffectiveResources.getDrawable(getEffectiveId(resid));
    }

    @Override
    public int getColor(int resid) {
        return mEffectiveResources.getColor(getEffectiveId(resid));
    }

    @Override
    public void changeThemeInfo(ThemeInfo themeInfo) {
        mTheme = themeInfo;
    }

    /**
     * 获取真正有效使用的资源id
     * @param resid
     * @return
     */
    protected int getEffectiveId(int resid) {
        int result = resid;
        if (resid > 0) {
            String entry = mEffectiveResources.getResourceEntryName(resid);
            String type = mEffectiveResources.getResourceTypeName(resid);
            String packageName = mTheme.getPackageName();
            result = mEffectiveResources.getIdentifier(entry + mTheme.getResNameSuffix(), type, packageName);
            // 未找到特定主题下的对应的资源, 使用默认主题的资源
            if (result == 0) {
                result = resid;
            }
        }
        return result;
    }
}
