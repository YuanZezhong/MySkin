package cn.sh.changxing.myskin.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sh.changxing.myskin.R;
import cn.sh.changxing.myskin.skin.OnThemeChangedListener;
import cn.sh.changxing.myskin.skin.ThemeInfo;
import cn.sh.changxing.myskin.skin.ThemeManager;
import cn.sh.changxing.myskin.skin.base.ThemeFragment;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

/**
 * Created by YuanZezhong on 2017/9/19.
 */
public class RecyclerViewFragment extends ThemeFragment implements OnThemeChangedListener {

    protected RecyclerView mRecyclerView;
    protected MyAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ThemeManager.getInstance().addOnThemeChangedListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ThemeManager.getInstance().removeOnThemeChangedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycleview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = ((RecyclerView) view.findViewById(R.id.rv_recyclerview));
        mAdapter = new MyAdapter(getActivityLayoutInflater());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        mAdapter.notifyDataSetChanged();
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
        private LayoutInflater mInflater;

        MyAdapter(LayoutInflater inflater) {
            mInflater = inflater;
        }

        @Override
        public MyAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(mInflater.inflate(R.layout.item_recyclerview, parent, false));
        }

        @Override
        public void onBindViewHolder(MyAdapter.Holder holder, int position) {
            holder.content.setText("item " + position);
            doAboutTheme(holder, position);
        }

        private void doAboutTheme(Holder holder, int position) {
            holder.content.setBackgroundColor(ThemeManager.getInstance().getCurrentResource().getColor(R.color.textView_background));
            holder.content.setTextColor(ThemeManager.getInstance().getCurrentResource().getColor(R.color.textView_textColor));
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class Holder extends RecyclerView.ViewHolder {
            public TextView content;

            public Holder(View itemView) {
                super(itemView);
                content = ((TextView) itemView.findViewById(R.id.tv_content));
            }
        }
    }
}
