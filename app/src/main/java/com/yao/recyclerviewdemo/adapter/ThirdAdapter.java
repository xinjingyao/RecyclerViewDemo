package com.yao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;
import com.yao.recyclerviewdemo.bean.DataModelOne;
import com.yao.recyclerviewdemo.bean.DataModelThree;
import com.yao.recyclerviewdemo.bean.DataModelTwo;
import com.yao.recyclerviewdemo.viewhodler.BaseViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeOneViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeThreeViewHolder;
import com.yao.recyclerviewdemo.viewhodler.TypeTwoViewHolder;
import com.yao.recyclerviewdemo.viewhodler.holder.BaseViewHolder2;
import com.yao.recyclerviewdemo.viewhodler.holder.TypeOneViewHolder2;
import com.yao.recyclerviewdemo.viewhodler.holder.TypeThreeViewHolder2;
import com.yao.recyclerviewdemo.viewhodler.holder.TypeTwoViewHolder2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yao on 2017/4/15.
 */

public class ThirdAdapter extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;


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
    private View view;

    private List<Integer> types = new ArrayList<>();
    Map<Integer, Integer> mPosition = new HashMap<>();

    private List<DataModelOne> list1;
    private List<DataModelTwo> list2;
    private List<DataModelThree> list3;

    public ThirdAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    /**
     * 设置数据
     * @param list1
     * @param list2
     * @param list3
     */
    public void setList(List<DataModelOne> list1, List<DataModelTwo> list2, List<DataModelThree> list3) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;

        setListByType(TYPE_ONE, list1);
        setListByType(TYPE_TWO, list2);
        setListByType(TYPE_THREE, list3);
        notifyDataSetChanged();
    }

    public void setListByType(int type, List list) {
        mPosition.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder2 vh = null;
        switch (viewType) {
            case DataModel.TYPE_ONE:
                view = inflater.inflate(R.layout.item_recyclerview_one, parent, false);
                vh = new TypeOneViewHolder2(view);
                break;
            case DataModel.TYPE_TWO:
                view = inflater.inflate(R.layout.item_recyclerview_two, parent, false);
                vh = new TypeTwoViewHolder2(view);
                break;
            case DataModel.TYPE_THREE:
                view = inflater.inflate(R.layout.item_recyclerview_three, parent, false);
                vh = new TypeThreeViewHolder2(view);
                break;
        }
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int realPosition = position - mPosition.get(viewType);
        BaseViewHolder2 vh = ((BaseViewHolder2) holder);
        switch (viewType) {
            case TYPE_ONE:
                vh.bindHolder(list1.get(realPosition));
                vh.itemView.setTag(list1.get(realPosition).content);
                break;
            case TYPE_TWO:
                vh.bindHolder(list2.get(realPosition));
                vh.itemView.setTag(list2.get(realPosition).content);
                break;
            case TYPE_THREE:
                vh.bindHolder(list3.get(realPosition));
                vh.itemView.setTag(list3.get(realPosition).content);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
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
