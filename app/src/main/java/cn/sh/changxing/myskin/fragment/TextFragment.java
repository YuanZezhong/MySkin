package cn.sh.changxing.myskin.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.base.ThemeFragment;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

/**
 * Created by YuanZezhong on 2017/9/20.
 */
public class TextFragment extends ThemeFragment implements OnThemeChangedListener {
    public static final String KEY_CONTENT = "content";

    protected String mContent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle data = getArguments();
        mContent = data.getString(KEY_CONTENT);
        LoggerFactory.getDefault().d("onAttach: {}", mContent);
    }

    public static TextFragment newInstance(String content) {
        TextFragment result = new TextFragment();
        Bundle data = new Bundle();
        data.putString(KEY_CONTENT, content);
        result.setArguments(data);
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggerFactory.getDefault().d("onCreate: {}", mContent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoggerFactory.getDefault().d("onCreateView: {}", mContent);
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.tv_content)).setText(mContent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoggerFactory.getDefault().d("onActivityCreated: {}", mContent);
    }

    @Override
    public void onStart() {
        super.onStart();
        LoggerFactory.getDefault().d("onStart: {}", mContent);
    }

    @Override
    public void onResume() {
        super.onResume();
        LoggerFactory.getDefault().d("onResume: {}", mContent);
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerFactory.getDefault().d("onPause: {}", mContent);
    }

    @Override
    public void onStop() {
        super.onStop();
        LoggerFactory.getDefault().d("onStop: {}", mContent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LoggerFactory.getDefault().d("onDestoryView: {}", mContent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggerFactory.getDefault().d("onDestroy: {}", mContent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LoggerFactory.getDefault().d("onDetach: {}", mContent);
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {

    }
}
