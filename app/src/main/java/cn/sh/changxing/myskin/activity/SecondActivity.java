package cn.sh.changxing.myskin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.fragment.TestFragment;
import cn.sh.changxing.myskin.skin.base.ThemeFragmentActivity;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

public class SecondActivity extends ThemeFragmentActivity implements View.OnClickListener {

    protected FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();
        init();
    }

    private void initViews() {
        mFragmentContainer = ((FrameLayout) findViewById(R.id.fl_fragment));
    }

    private void init() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_fragment, new TestFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
