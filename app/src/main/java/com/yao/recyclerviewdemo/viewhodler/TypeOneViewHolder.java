package com.yao.recyclerviewdemo.viewhodler;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;

/**
 * Created by yao on 2017/4/15.
 */

public class TypeOneViewHolder extends BaseViewHolder {

    public ImageView imgLeft;
    public TextView content;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        imgLeft = (ImageView) itemView.findViewById(R.id.img_left);
        content = (TextView) itemView.findViewById(R.id.tv_content);

        // 设置背景
        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindHolder(DataModel data) {
        imgLeft.setImageResource(data.imageLeft);
        content.setText(data.content);
    }


}
