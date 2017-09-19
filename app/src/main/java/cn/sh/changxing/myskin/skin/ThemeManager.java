package cn.sh.changxing.myskin.skin;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.sh.changxing.myskin.skin.resource.MultiResourceParser;
import cn.sh.changxing.myskin.skin.resource.ResourceParser;
import cn.sh.changxing.myskin.skin.resource.SingleResourceParser;

/**
 * Created by yuanyi on 17-9-16.
 */

public class ThemeManager {
    public static final String THEME_NAME_DEFAULT = null;
    public static final int MODE_SINGLE_APP = 0;
    public static final int MODE_MULTI_APP = 1;

    private static ThemeManager sInstance;

    private Context mContext;
    /**
     * 主题管理的模式, 分为两种:
     * 1. 只是用一个APP管理所有的主题相关资源
     * 2. 使用多套主题文件管理不同的主题相关资源
     */
    private int mMode;
    /**
     * 当前所支持的所有主题信息
     */
    private Map<String, ThemeInfo> mThemeInfos;
    /**
     * 注册的主题改变监听器
     */
    private Map<String, OnThemeChangedListener> mListeners;
    /**
     * 系统默认的主题信息, 不会改变
     */
    private ThemeInfo mDefaultTheme;
    /**
     * 当前使用的主题信息, 会改变
     */
    private ThemeInfo mCurrentTheme;
    /**
     * 系统默认主题使用的资源解析器
     */
    private ResourceParser mDefaultResource;
    /**
     * 当前主题使用的资源解析器
     */
    private ResourceParser mCurrentResource;
    private Resources mOriginalResource;

    private ThemeManager() {
        mThemeInfos = new HashMap<>();
        mListeners = new HashMap<>();
    }

    public void init(Context context) {
        init(context, MODE_SINGLE_APP);
    }

    private void init(Context context, int mode) {
        mContext = context.getApplicationContext();
        mMode = mode == MODE_MULTI_APP ? MODE_MULTI_APP : MODE_SINGLE_APP;
        ThemeInfo defaultTheme = new ThemeInfo(THEME_NAME_DEFAULT, "", context.getPackageName());
        addThemeInfo(defaultTheme);
        mCurrentTheme = mDefaultTheme = defaultTheme;
        initResources();
    }

    private void initResources() {
        mOriginalResource = mContext.getResources();
        mDefaultResource = new SingleResourceParser(mOriginalResource, mDefaultTheme);
        if (mMode == MODE_SINGLE_APP) {
            mCurrentResource = new SingleResourceParser(mOriginalResource, mDefaultTheme);
        } else {
            mCurrentResource = new MultiResourceParser(mOriginalResource, mDefaultTheme);
        }
    }

    public static ThemeManager getInstance() {
        if (sInstance == null) {
            synchronized (ThemeManager.class) {
                if (sInstance == null) {
                    sInstance = new ThemeManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取当前使用的主题
     *
     * @return 当前使用的主题信息
     */
    public ThemeInfo getCurrentTheme() {
        return mCurrentTheme;
    }

    /**
     * 获取默认的主题信息
     *
     * @return
     */
    public ThemeInfo getDefaultTheme() {
        return mDefaultTheme;
    }

    /**
     * 获取默认主题的资源解析器
     *
     * @return 默认的资源解析器
     */
    public ResourceParser getDefaultResource() {
        return mDefaultResource;
    }

    /**
     * 获取当前主题的资源解析器
     *
     * @return 默认的资源解析器
     */
    public ResourceParser getCurrentResource() {
        return mCurrentResource;
    }

    /**
     * 获取最原始的资源解析器
     * @return
     */
    public Resources getOriginalResource() {
        return mOriginalResource;
    }

    /**
     * 当前使用的主题是否是默认主题
     *
     * @return true:是, false:不是
     */
    public boolean isDefaultTheme() {
        return mCurrentTheme.equals(mDefaultTheme);
    }

    /**
     * 判断指定主题是否为当前使用的主题
     * @return true:是, false:不是
     */
    public boolean isCurrentTheme(ThemeInfo themeInfo) {
        return mCurrentTheme.equals(themeInfo);
    }

    /**
     * 添加主题
     *
     * @param theme
     */
    public void addThemeInfo(ThemeInfo theme) {
        if (!mThemeInfos.containsKey(theme.getThemeName())) {
            mThemeInfos.put(theme.getThemeName(), theme);
        }
    }

    /**
     * 更改当前的使用的主题为指定的主题
     *
     * @param themeName 更改后的主题名称
     */
    public void changeThemeTo(String themeName) {
        ThemeInfo themeInfo = mThemeInfos.get(themeName);
        if (themeInfo != null && themeInfo != mCurrentTheme) {
            mCurrentResource.changeThemeInfo(themeInfo);
            ThemeInfo oldTheme = mCurrentTheme;
            mCurrentTheme = themeInfo;
            notifyThemeChanged(oldTheme, mCurrentTheme);
        }
    }

    /**
     * 添加主题改变监听器
     * @param listener
     */
    public void addOnThemeChangedListener(OnThemeChangedListener listener) {
        if (listener != null) {
            String key = listener.toString();
            if (!mListeners.containsKey(key)) {
                mListeners.put(key, listener);
            }
        }
    }

    /**
     * 移除主题改变监听器
     * @param listener
     */
    public void removeOnThemeChangedListener(OnThemeChangedListener listener) {
        if (listener != null) {
            String key = listener.toString();
            mListeners.remove(key);
        }
    }

    private void notifyThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        Set<Map.Entry<String, OnThemeChangedListener>> listeners = mListeners.entrySet();
        for (Map.Entry<String, OnThemeChangedListener> listener : listeners) {
            listener.getValue().onThemeChanged(oldTheme, newTheme);
        }
    }
}
