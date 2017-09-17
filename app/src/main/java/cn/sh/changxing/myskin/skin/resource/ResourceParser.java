package cn.sh.changxing.myskin.skin.resource;

import android.graphics.drawable.Drawable;

import cn.sh.changxing.myskin.skin.ThemeInfo;

/**
 * Created by yuanyi on 17-9-16.
 */

public interface ResourceParser {
    String getString(int resid);

    Drawable getDrawable(int resid);

    int getColor(int resid);

    void changeThemeInfo(ThemeInfo themeInfo);
}
