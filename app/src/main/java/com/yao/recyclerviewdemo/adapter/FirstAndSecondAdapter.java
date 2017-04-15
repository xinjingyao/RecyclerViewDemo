package com.yao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;
import com.yao.recyclerviewdemo.viewhodler.BaseViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeOneViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeThreeViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yao on 2017/4/15.
 */

public class FirstAndSecondAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<DataModel> mList = new ArrayList<>();

    public FirstAndSecondAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(inflater.inflate(R.layout.item_recyclerview_one,
                        parent, false));
            case DataModel.TYPE_TWO:
                return new TypeTwoViewHolder(inflater.inflate(R.layout.item_recyclerview_two,
                        parent, false));
            case DataModel.TYPE_THREE:
                return new TypeThreeViewHolder(inflater.inflate(R.layout.item_recyclerview_three,
                        parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder)holder).bindHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
