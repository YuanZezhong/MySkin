package cn.sh.changxing.myskin.skin.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInfo;

/**
 * Created by YuanZezhong on 2017/9/19.
 *
 * 此Fragment必须和ThemeFragmentActivity一起使用
 */
public abstract class ThemeFragment extends Fragment implements OnThemeChangedListener {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof ThemeFragmentActivity)) {
            throw new RuntimeException("ThemeFragment must be used with ThemeFramentActivity!");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getView();
        if (view != null) {
            removeAllViewAttrs(view);
        }
    }

    /**
     * 获取宿主Activity的LayoutInflater
     *
     * @return LayoutInflater
     */
    public LayoutInflater getActivityLayoutInflater() {
        return getActivity().getLayoutInflater();
    }

    /**
     * 清理此Fragment中所有对应改变主题的View
     */
    protected void removeAllViewAttrs(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                removeAllViewAttrs(viewGroup.getChildAt(i));
            }
        } else {
            ((ThemeFragmentActivity) getActivity()).getViewAttrManager().removeViewAttrItem(view);
        }
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {

    }
}
