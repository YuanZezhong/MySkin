package cn.sh.changxing.myskin;

import android.app.Application;

import cn.sh.changxing.myskin.attr.TextAttr;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.attr.AttrFactory;

/**
 * Created by yuanyi on 17-9-17.
 */

public class MainApplication extends Application {
    private ThemeManager mTM;

    @Override
    public void onCreate() {
        super.onCreate();
        initThemeManager();
    }

    private void initThemeManager() {
        mTM = ThemeManager.getInstance();
        mTM.init(this);
        mTM.addThemeInfo(new ThemeInfo("红色主题", "_red", getPackageName()));
        AttrFactory.addSupportAttr("text", TextAttr.class);
    }
}
