package com.yao.recyclerviewdemo.viewhodler.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yao.recyclerviewdemo.bean.DataModel;

/**
 * Created by yao on 2017/4/15.
 */

public abstract class BaseViewHolder2<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder2(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T data);
}
