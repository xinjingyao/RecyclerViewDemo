package com.yao.recyclerviewdemo.viewhodler;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;

/**
 * Created by yao on 2017/4/15.
 */

public class TypeTwoViewHolder extends BaseViewHolder {

    public ImageView imgLeft;
    public TextView content;
    public TextView title;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        imgLeft = (ImageView) itemView.findViewById(R.id.img_left);
        content = (TextView) itemView.findViewById(R.id.tv_content);
        title = (TextView) itemView.findViewById(R.id.tv_title);

        itemView.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public void bindHolder(DataModel data) {
        imgLeft.setImageResource(data.imageLeft);
        content.setText(data.content);
        title.setText(data.title);
    }


}
