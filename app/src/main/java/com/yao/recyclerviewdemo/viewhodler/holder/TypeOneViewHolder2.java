package com.yao.recyclerviewdemo.viewhodler.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;
import com.yao.recyclerviewdemo.bean.DataModelOne;

/**
 * Created by yao on 2017/4/15.
 */

public class TypeOneViewHolder2 extends BaseViewHolder2<DataModelOne> {

    public ImageView imgLeft;
    public TextView content;

    public TypeOneViewHolder2(View itemView) {
        super(itemView);
        imgLeft = (ImageView) itemView.findViewById(R.id.img_left);
        content = (TextView) itemView.findViewById(R.id.tv_content);

        // 设置背景
        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindHolder(DataModelOne data) {
        imgLeft.setImageResource(data.imageLeft);
        content.setText(data.content);
    }


}
