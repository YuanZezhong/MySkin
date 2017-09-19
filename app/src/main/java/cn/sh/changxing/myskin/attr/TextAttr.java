package cn.sh.changxing.myskin.attr;

import android.view.View;
import android.widget.TextView;

import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.attr.Attr;

/**
 * Created by yuanyi on 17-9-17.
 */

public class TextAttr extends Attr {

    @Override
    public void apply(View v) {
        if (isStringType()) {
            if (v instanceof TextView) {
                TextView textView = (TextView) v;
                textView.setText(ThemeManager.getInstance().getCurrentResource().getString(valueResId));
            }
        }
    }
}
