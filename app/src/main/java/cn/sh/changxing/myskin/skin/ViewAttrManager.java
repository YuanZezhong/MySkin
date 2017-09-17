package cn.sh.changxing.myskin.skin;

import android.view.View;

/**
 * Created by yuanyi on 17-9-17.
 */

public interface ViewAttrManager {
    void addViewAttrItem(ViewAttrItem item);

    void removeViewAttrItem(ViewAttrItem item);

    void addViewAttrItem(View view, String attrName, int resid);

    void removeViewAttrItem(View view);

    void clear();

    void applyThemeChange();
}
