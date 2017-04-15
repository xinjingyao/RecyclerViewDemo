package com.yao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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

public class FirstAndSecondAdapter extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {



    /**
     * 创建接口
     */
    public interface OnRecyclerViewItemClickLisenter {
        void onItemClick(View view, String content);
        void onItemLongClick(View view, String content);
    }
    private OnRecyclerViewItemClickLisenter mOnItemClickLisenter;
    public void setOnItemClickLisenter(OnRecyclerViewItemClickLisenter lisenter) {
        mOnItemClickLisenter = lisenter;
    }

    private LayoutInflater inflater;
    private List<DataModel> mList = new ArrayList<>();
    private View view;


    public FirstAndSecondAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setList(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;
        switch (viewType) {
            case DataModel.TYPE_ONE:
                view = inflater.inflate(R.layout.item_recyclerview_one, parent, false);
                vh = new TypeOneViewHolder(view);
                break;
            case DataModel.TYPE_TWO:
                view = inflater.inflate(R.layout.item_recyclerview_two, parent, false);
                vh = new TypeTwoViewHolder(view);
                break;
            case DataModel.TYPE_THREE:
                view = inflater.inflate(R.layout.item_recyclerview_three, parent, false);
                vh = new TypeThreeViewHolder(view);
                break;
        }
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder)holder).bindHolder(mList.get(position));
        ((BaseViewHolder)holder).itemView.setTag(mList.get(position).content);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void onClick(View view) {
        if (mOnItemClickLisenter != null) {
            mOnItemClickLisenter.onItemClick(view, (String) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickLisenter != null) {
            mOnItemClickLisenter.onItemLongClick(view, (String) view.getTag());
            return true;
        }
        return false;
    }

}
