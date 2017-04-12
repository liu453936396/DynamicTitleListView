package com.zhaohui.dynamictitlelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    TitleListView listView;
    List<Entry> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (TitleListView) findViewById(R.id.listView);
        mList = initData();
        TitledListAdapter adapter = new TitledListAdapter(this, mList);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
    }

    private List<Entry> initData() {
        List<Entry> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 60; i++) {
            Entry entry = new Entry();
            entry.setContent(String.valueOf(random.nextInt(20)));
            entry.setTitle("分组" + random.nextInt(10));
            list.add(entry);
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 第一项与第二项标题不同，说明标题需要移动
        if (!mList.get(firstVisibleItem).getTitle().equals(mList.get(firstVisibleItem + 1).getTitle())) {
            ((TitleListView) view).moveTitle(mList.get(firstVisibleItem).getTitle());
        } else {
            ((TitleListView) view).updateTitle(mList.get(firstVisibleItem).getTitle());
        }

    }
}
