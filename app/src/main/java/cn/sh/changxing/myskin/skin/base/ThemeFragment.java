package cn.sh.changxing.myskin.skin.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by YuanZezhong on 2017/9/19.
 * <p>
 * 1.此Fragment必须和ThemeFragmentActivity一起使用
 * <p>
 * 2.此Fragment中对应主题更换的View应交给宿主Activity的ViewAttrManager管理
 */
public abstract class ThemeFragment extends Fragment {

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
            onRemoveAllViewAttrs(view);
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
     * 清理此Fragment中所有对应主题更换的View
     * <p>
     * 默认实现是从根View开始递归清理
     */
    protected void onRemoveAllViewAttrs(View view) {
        ((ThemeFragmentActivity) getActivity()).getViewAttrManager().removeViewAttrItem(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                onRemoveAllViewAttrs(viewGroup.getChildAt(i));
            }
        }
    }
}
