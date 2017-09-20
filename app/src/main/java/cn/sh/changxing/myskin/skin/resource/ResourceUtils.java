package cn.sh.changxing.myskin.skin.resource;

import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import cn.sh.changxing.myskin.skin.ThemeInfo;

/**
 * Created by yuanyi on 17-9-17.
 */

public class ResourceUtils {
    private ResourceUtils() {

    }

    public static Resources createResources(Resources original, ThemeInfo themeInfo) {
        Class<AssetManager> assetManagerClass = AssetManager.class;
        try {
            Constructor<AssetManager> constructor = assetManagerClass.getConstructor();
            constructor.setAccessible(true);
            Method addAssetPath = assetManagerClass.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            AssetManager assetManager = constructor.newInstance();
            Integer ret = (Integer) addAssetPath.invoke(assetManager, themeInfo.getThemePath());
            if (ret != 0) {
                return new Resources(assetManager, original.getDisplayMetrics(), original.getConfiguration());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
