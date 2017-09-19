package cn.sh.changxing.myskin.skin.attr;

import android.view.View;
import android.widget.ImageView;

import cn.sh.changxing.myskin.skin.ThemeManager;

/**
 * Created by YuanZezhong on 2017/9/19.
 */
public class SrcAttr extends Attr {
    @Override
    public void apply(View view) {
        if (view instanceof ImageView) {
            ImageView iv = (ImageView) view;
            if (isDrawableType() || isMipmapType()) {
                iv.setImageDrawable(ThemeManager.getInstance().getCurrentResource().getDrawable(valueResId));
            }
        }
    }
}
