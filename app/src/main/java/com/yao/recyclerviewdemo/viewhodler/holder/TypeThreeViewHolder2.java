package com.yao.recyclerviewdemo.viewhodler.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yao.recyclerviewdemo.R;
import com.yao.recyclerviewdemo.bean.DataModel;
import com.yao.recyclerviewdemo.bean.DataModelThree;

/**
 * Created by yao on 2017/4/15.
 */

public class TypeThreeViewHolder2 extends BaseViewHolder2<DataModelThree> {

    public ImageView imgLeft;
    public TextView content;
    public TextView title;
    public ImageView imgRight;

    public TypeThreeViewHolder2(View itemView) {
        super(itemView);
        imgLeft = (ImageView) itemView.findViewById(R.id.img_left);
        content = (TextView) itemView.findViewById(R.id.tv_content);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        imgRight = (ImageView) itemView.findViewById(R.id.img_right);

        itemView.setBackgroundColor(Color.DKGRAY);
    }

    @Override
    public void bindHolder(DataModelThree data) {
        imgLeft.setImageResource(data.imageLeft);
        content.setText(data.content);
        title.setText(data.title);
        imgRight.setImageResource(data.imageRight);
    }


}
