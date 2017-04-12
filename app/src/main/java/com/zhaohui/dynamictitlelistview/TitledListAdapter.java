package com.zhaohui.dynamictitlelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by E7240A on 2017-04-11.
 */

class TitledListAdapter extends BaseAdapter {

    private List<Entry> datas;
    private Context context;
    private LayoutInflater inflater;

    public TitledListAdapter(Context context, List<Entry> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HandlerView handlerView;
        if (view == null) {
            view = inflater.inflate(R.layout.listitem, null);
            handlerView = new HandlerView(view);
            view.setTag(handlerView);
        } else {
            handlerView = (HandlerView) view.getTag();
        }
        handlerView.content.setText(datas.get(position).getContent());
        handlerView.title.setText(datas.get(position).getTitle());
        //第一项和前后不同的项需要显示标题，否则隐藏
        if (position == 0) {
            handlerView.title.setVisibility(View.VISIBLE);
        } else if (position < getCount() && !datas.get(position).getTitle().equals(datas.get(position - 1).getTitle())) {
            handlerView.title.setVisibility(View.VISIBLE);
        } else {
            handlerView.title.setVisibility(View.GONE);
        }
        return view;
    }

    class HandlerView {

        TextView content;
        TextView title;

        public HandlerView(View view) {
            content = (TextView) view.findViewById(R.id.content);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
