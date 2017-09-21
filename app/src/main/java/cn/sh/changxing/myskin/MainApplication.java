package cn.sh.changxing.myskin;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import cn.sh.changxing.myskin.attr.TextAttr;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.attr.AttrFactory;
import cn.sh.changxing.myskin.skin.resource.ResourceUtils;

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
//        mTM.init(this, ThemeManager.MODE_MULTI_APP);  // 多种主题使用多个资源包的模式
//        mTM.addThemeInfo(new ThemeInfo("红色主题", "/sdcard/redtheme.skin")); // 多个资源包的模式下的主题信息需要提供资源包路径
        mTM.init(this);     // 多种主题使用同一个资源包的模式
        mTM.addThemeInfo(new ThemeInfo("红色主题", "_red", getPackageName()));  // 单个资源包的模式下的主题信息需要提供资源名的后缀, 如: R.color.text_bg -> R.color.text_bg_red
        AttrFactory.addSupportAttr("text", TextAttr.class);     // 添加更多的属性支持
    }
}
