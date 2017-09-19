package cn.sh.changxing.myskin.activity;

import android.content.Context;
import android.content.Intent;
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
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.base.ThemeFragmentActivity;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

public class MainActivity extends ThemeFragmentActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView mTitleTv;
    private Button mRedBtn;
    private Button mOriginalBtn;
    private ListView mListView;
    protected Button mUpdateBtn;
    protected MyAdapter mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mAdatper = new MyAdapter(this);
        mListView.setAdapter(mAdatper);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
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
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        super.onThemeChanged(oldTheme, newTheme);
        mAdatper.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdatper.mDatas.remove(position);
        mAdatper.notifyDataSetChanged();
    }

    static class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<String> mDatas;

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
            doAboutTheme(holder, position);
            return convertView;
        }

        private void doAboutTheme(ViewHolder holder, int position) {
            holder.content.setBackgroundColor(ThemeManager.getInstance().getCurrentResource().getColor(R.color.textView_background));
            holder.content.setTextColor(ThemeManager.getInstance().getCurrentResource().getColor(R.color.textView_textColor));
        }

        static class ViewHolder {
            public final TextView content;

            ViewHolder(View itemView) {
                content = ((TextView) itemView.findViewById(R.id.tv_content));
            }
        }
    }
}
