package com.yao.recyclerviewdemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.adapter.FirstAndSecondAdapter;
import com.yao.recyclerviewdemo.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private FirstAndSecondAdapter mFirstAndSecondAdapter;
    private int[] colors = {android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light};
    private boolean isLoadingMore;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        initRecyclerView();
        initSwipeRefreshLayout();
        initRecyclerViewLisenter();
    }

    /**
     * 初始化刷新控件
     */
    private void initSwipeRefreshLayout() {
        /*
         * true:progressbar可以缩放
         * start：开始Y轴
         * end：结束Y轴
         */
        swipeRefresh.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        // 设置progressbar的颜色
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        //设置第一次进来页面是刷新
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
                onRefresh();
            }
        });

        swipeRefresh.setOnRefreshListener(this);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        mFirstAndSecondAdapter = new FirstAndSecondAdapter(this);

        //表格样式
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(gridLayoutManager);
        // 设置间隔
        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutParams
                        = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                // 一行有几个view
                int spanSize = layoutParams.getSpanSize();
                // 是一行中的第几个
                int spanIndex = layoutParams.getSpanIndex();

                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()) {// 说明一行两个
                    if (spanIndex == 0) {// 左边
                        outRect.right = 10;
                    } else {
                        outRect.left = 10;
                    }
                }
            }
        });

        // 设置列数
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {// 这里面返回的列数相当于设置列数2的分子
                int type = recyclerview.getAdapter().getItemViewType(position);
                // 如果是第三种布局或者是脚布局，就占用一列
                if (type == DataModel.TYPE_THREE || type == FirstAndSecondAdapter.TYPE_FOOT) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        recyclerview.setAdapter(mFirstAndSecondAdapter);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 最后一个显示的item的下标
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mFirstAndSecondAdapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean refreshing = swipeRefresh.isRefreshing();
                    if (refreshing) {
                        // 移除foot View
                        mFirstAndSecondAdapter.notifyItemRemoved(mFirstAndSecondAdapter.getItemCount());
                        return;
                    }
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                isLoadingMore = false;
                                mFirstAndSecondAdapter.notifyItemRemoved(mFirstAndSecondAdapter.getItemCount());
                                Log.d("test", "load more completed");
                            }
                        }, 1500);
                    }
                }
            }
        });
    }

    /**
     * 设置RecyclerView的监听事件
     */
    private void initRecyclerViewLisenter() {
        mFirstAndSecondAdapter.setOnItemClickLisenter(new FirstAndSecondAdapter.OnRecyclerViewItemClickLisenter() {
            @Override
            public void onItemClick(View view, String content) {
                Toast.makeText(SecondActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, String content) {
                Toast.makeText(SecondActivity.this, "长按--" + content, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int type = (int) (Math.random() * 3 + 1);
            DataModel data = new DataModel();
            data.type = type;
            data.imageLeft = colors[type - 1];
            data.title = "title: " + i;
            data.content = "content: " + i;
            data.imageRight = colors[(type + 1) % 3];

            list.add(data);
        }
        mFirstAndSecondAdapter.setList(list);

    }

    @Override
    public void onRefresh() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(2000);
                e.onNext("刷新成功");
            }
        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<String>() {
              @Override
              public void accept(String s) throws Exception {
                  initData();
                  swipeRefresh.setRefreshing(false);
                  Toast.makeText(SecondActivity.this, s, Toast.LENGTH_SHORT).show();
              }
          });

    }
}
