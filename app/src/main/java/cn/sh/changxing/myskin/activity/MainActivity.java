package cn.sh.changxing.myskin.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.skin.AppendViewAttrManager;
import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.ViewAttrManager;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

public class MainActivity extends Activity implements View.OnClickListener, OnThemeChangedListener {

    private TextView tv;
    private Button mRedBtn;
    private Button mOriginalBtn;
    private AppendViewAttrManager mViewAttrManager;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        init();
    }

    private void initViews() {
        tv = ((TextView) findViewById(R.id.tv_title));
        mRedBtn = ((Button) findViewById(R.id.btn_red));
        mOriginalBtn = ((Button) findViewById(R.id.btn_original));
        mListView = ((ListView) findViewById(R.id.lv_items));
        mRedBtn.setOnClickListener(this);
        mOriginalBtn.setOnClickListener(this);
    }

    private void init() {
        ThemeManager.getInstance().addOnThemeChangedListener(this);
        mViewAttrManager = new AppendViewAttrManager();
        mViewAttrManager.addViewAttrItem(tv, "background", R.color.textView_background);
        mViewAttrManager.addViewAttrItem(tv, "textColor", R.color.textView_textColor);
        mViewAttrManager.addViewAttrItem(tv, "text", R.string.app_name);
        mViewAttrManager.addViewAttrItem(mRedBtn, "background", R.drawable.slt_button);

        mListView.setAdapter(new MyAdapter(this, mViewAttrManager));
    }

    @Override
    protected void onDestroy() {
        mViewAttrManager.clear();
        ThemeManager.getInstance().removeOnThemeChangedListener(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                ThemeManager.getInstance().changeThemeTo("红色主题");
                break;
            case R.id.btn_original:
                ThemeManager.getInstance().changeThemeTo(null);
                break;
        }
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        mViewAttrManager.applyThemeChange();
    }

    static class MyAdapter extends BaseAdapter {
        private Context mContext;
        private ViewAttrManager mManager;
        private LayoutInflater mInflater;

        MyAdapter(Context context, ViewAttrManager manager) {
            mContext = context;
            mManager = manager;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return "Item " + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_listview, parent, false);
                holder = new ViewHolder(convertView);
                mManager.addViewAttrItem(holder.content, "background", R.color.textView_background);
                mManager.addViewAttrItem(holder.content, "textColor", R.color.textView_textColor);
                LoggerFactory.getDefault().d("inflate position is {}", position);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.content.setText(getItem(position).toString());
            return convertView;
        }

        static class ViewHolder {
            public final TextView content;

            ViewHolder(View itemView) {
                content = ((TextView) itemView.findViewById(R.id.tv_content));
            }
        }
    }
}
