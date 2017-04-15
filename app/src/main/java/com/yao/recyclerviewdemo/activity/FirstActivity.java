package com.yao.recyclerviewdemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.adapter.FirstAndSecondAdapter;
import com.yao.recyclerviewdemo.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private FirstAndSecondAdapter mFirstAndSecondAdapter;
    private int[] colors = {android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        mFirstAndSecondAdapter = new FirstAndSecondAdapter(this);

        // 列表样式
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        // 设置间隔
        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
            }
        });

//        recyclerview.setBackgroundColor(Color.GRAY);
        recyclerview.setAdapter(mFirstAndSecondAdapter);
        initData();
        initRecyclerViewLisenter();
    }

    /**
     * 设置RecyclerView的监听事件
     */
    private void initRecyclerViewLisenter() {
        mFirstAndSecondAdapter.setOnItemClickLisenter(new FirstAndSecondAdapter.OnRecyclerViewItemClickLisenter() {
            @Override
            public void onItemClick(View view, String content) {
                Toast.makeText(FirstActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, String content) {
                Toast.makeText(FirstActivity.this, "长按--" + content, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int type = (int) (Math.random() * 3 + 1);
            DataModel data = new DataModel();
            data.type = type;
            data.imageLeft = colors[type -1];
            data.title = "title: " + i;
            data.content = "content: " + i;
            data.imageRight = colors[(type + 1) % 3];

            list.add(data);
        }
        mFirstAndSecondAdapter.setList(list);

    }
}
