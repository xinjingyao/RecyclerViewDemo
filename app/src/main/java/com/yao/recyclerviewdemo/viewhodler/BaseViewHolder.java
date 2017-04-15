package com.yao.recyclerviewdemo.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yao.recyclerviewdemo.bean.DataModel;

/**
 * Created by yao on 2017/4/15.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(DataModel data);
}
