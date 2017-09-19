package cn.sh.changxing.myskin.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInflaterFactory;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

public class MainActivity extends Activity implements View.OnClickListener, OnThemeChangedListener, AdapterView.OnItemClickListener {

    private TextView mTitleTv;
    private Button mRedBtn;
    private Button mOriginalBtn;
    private ListView mListView;
    protected ThemeInflaterFactory mFactory;
    protected Button mUpdateBtn;
    protected MyAdapter mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFactory = new ThemeInflaterFactory(getLayoutInflater());
        getLayoutInflater().setFactory(mFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        init();
    }

    private void initViews() {
        mTitleTv = ((TextView) findViewById(R.id.tv_title));
        mRedBtn = ((Button) findViewById(R.id.btn_red));
        mOriginalBtn = ((Button) findViewById(R.id.btn_original));
        mListView = ((ListView) findViewById(R.id.lv_items));
        mUpdateBtn = ((Button) findViewById(R.id.btn_update_listview));
        mUpdateBtn.setOnClickListener(this);
        mRedBtn.setOnClickListener(this);
        mOriginalBtn.setOnClickListener(this);
    }

    private void init() {
        ThemeManager.getInstance().addOnThemeChangedListener(this);
        mAdatper = new MyAdapter(this);
        mListView.setAdapter(mAdatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mFactory.getViewAttrManager().clear();
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
                ThemeManager.getInstance().changeThemeTo(ThemeManager.THEME_NAME_DEFAULT);
                break;
            case R.id.btn_update_listview:
                break;
        }
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        mFactory.getViewAttrManager().applyThemeChange();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LoggerFactory.getDefault().d("clicked position is {}, id is {}", position, id);
//        mAdatper.mDatas.remove(position);
//        mAdatper.notifyDataSetChanged();
    }

    static class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        protected ArrayList<String> mDatas;

        MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mDatas = new ArrayList<>(30);
            initDatas();
        }

        private void initDatas() {
            for (int i = 0; i < 30; i++) {
                mDatas.add("Item " + i);
            }
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
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
