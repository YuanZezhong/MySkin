package cn.sh.changxing.myskin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.base.ThemeFragment;

public class TestFragment extends ThemeFragment implements View.OnClickListener {

    protected Button mRedBtn;
    protected Button mOriginalBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRedBtn = ((Button) view.findViewById(R.id.btn_red));
        mOriginalBtn = ((Button) view.findViewById(R.id.btn_original));
        mRedBtn.setOnClickListener(this);
        mOriginalBtn.setOnClickListener(this);
        FragmentManager childFragmentManager = getChildFragmentManager();
        childFragmentManager.beginTransaction().replace(R.id.fl_fragment, new RecyclerViewFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                ThemeManager.getInstance().changeTheme("红色主题");
                break;
            case R.id.btn_original:
                ThemeManager.getInstance().changeTheme(ThemeManager.THEME_NAME_DEFAULT);
                break;
        }
    }
}
