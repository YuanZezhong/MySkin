package cn.sh.changxing.myskin.skin.resource;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimRes;
import android.support.annotation.AnyRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FractionRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.annotation.XmlRes;
import android.util.TypedValue;

import java.io.InputStream;

import cn.sh.changxing.myskin.skin.ThemeInfo;

/**
 * Created by yuanyi on 17-9-16.
 */

public class MultiResourceParser implements ChangeableResourceParser {
    private Resources mOriginalResources;
    private Resources mEffectiveResources;
    private ThemeInfo mThemeInfo;

    public MultiResourceParser(Resources resources, ThemeInfo themeInfo) {
        mOriginalResources = mEffectiveResources = resources;
        mThemeInfo = themeInfo;
    }

    @NonNull
    @Override
    public CharSequence getText(@StringRes int id) throws Resources.NotFoundException {
        return null;
    }

    @NonNull
    @Override
    public CharSequence getQuantityText(@PluralsRes int id, int quantity) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public String getString(int resid) {
        return null;
    }

    @NonNull
    @Override
    public String getString(@StringRes int id, Object... formatArgs) throws Resources.NotFoundException {
        return null;
    }

    @NonNull
    @Override
    public String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public CharSequence getText(@StringRes int id, CharSequence def) {
        return null;
    }

    @NonNull
    @Override
    public CharSequence[] getTextArray(@ArrayRes int id) throws Resources.NotFoundException {
        return new CharSequence[0];
    }

    @NonNull
    @Override
    public String[] getStringArray(@ArrayRes int id) throws Resources.NotFoundException {
        return new String[0];
    }

    @NonNull
    @Override
    public int[] getIntArray(@ArrayRes int id) throws Resources.NotFoundException {
        return new int[0];
    }

    @NonNull
    @Override
    public TypedArray obtainTypedArray(@ArrayRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public float getDimension(@DimenRes int id) throws Resources.NotFoundException {
        return 0;
    }

    @Override
    public int getDimensionPixelOffset(@DimenRes int id) throws Resources.NotFoundException {
        return 0;
    }

    @Override
    public int getDimensionPixelSize(@DimenRes int id) throws Resources.NotFoundException {
        return 0;
    }

    @Override
    public float getFraction(@FractionRes int id, int base, int pbase) {
        return 0;
    }

    @Override
    public Drawable getDrawable(int resid) {
        return null;
    }

    @Override
    public Drawable getDrawable(@DrawableRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density, @Nullable Resources.Theme theme) {
        return null;
    }

    @Override
    public Movie getMovie(@RawRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public int getColor(int resid) {
        return 0;
    }

    @Override
    public int getColor(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return 0;
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public boolean getBoolean(@BoolRes int id) throws Resources.NotFoundException {
        return false;
    }

    @Override
    public int getInteger(@IntegerRes int id) throws Resources.NotFoundException {
        return 0;
    }

    @Override
    public XmlResourceParser getLayout(@LayoutRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public XmlResourceParser getAnimation(@AnimRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public XmlResourceParser getXml(@XmlRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public InputStream openRawResource(@RawRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public InputStream openRawResource(@RawRes int id, TypedValue value) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public AssetFileDescriptor openRawResourceFd(@RawRes int id) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public void getValue(@AnyRes int id, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {

    }

    @Override
    public void getValueForDensity(@AnyRes int id, int density, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {

    }

    @Override
    public int getIdentifier(String name, String defType, String defPackage) {
        return 0;
    }

    @Override
    public String getResourceName(@AnyRes int resid) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public String getResourcePackageName(@AnyRes int resid) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public String getResourceTypeName(@AnyRes int resid) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public String getResourceEntryName(@AnyRes int resid) throws Resources.NotFoundException {
        return null;
    }

    @Override
    public void changeThemeInfo(ThemeInfo themeInfo) {

    }

    @Override
    public void changeThemeInfo(Resources themeResource, ThemeInfo themeInfo) {
        mEffectiveResources = themeResource;
        mThemeInfo = themeInfo;
    }

    @Override
    public int getEffectiveId(int resid) {
        return 0;
    }
}
