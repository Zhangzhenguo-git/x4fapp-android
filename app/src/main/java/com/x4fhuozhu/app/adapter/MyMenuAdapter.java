package com.x4fhuozhu.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.x4fhuozhu.app.R;
import com.x4fhuozhu.app.bean.ModelMenuItem;

import java.util.List;

/**
 * @author Zhangzhenguo
 * @create 2019/8/23
 * @Email 18311371235@163.com
 * @Describe
 */
public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ModelMenuItem> mValues;

    public MyMenuAdapter(Activity activity,List<ModelMenuItem> items) {
        mValues = items;
        this.activity=activity;
    }

    @Override
    public MyMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mItem = mValues.get(i);

        viewHolder.tvName.setText(viewHolder.mItem.getName());
        setTextTypeFace(viewHolder.imageView,viewHolder.mItem.getImage());
//        Glide.with(context).
//                load(viewHolder.mItem.getImage())
//                .thumbnail(0.5f)
//                .into(viewHolder.imageView);

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //功能权限
                Class activity = viewHolder.mItem.getActivity();
                if (activity == null) {
                    return;
                }
                Intent intent = new Intent(context, activity);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView imageView;
        TextView tvName;
        public ModelMenuItem mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.imageView = (TextView) itemView.findViewById(R.id.iv_My_Item_Icon);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_My_Item_Title);
//                badge = new QBadgeView(context).setBadgePadding(5, true).bindTarget(imageView);

        }
    }
    /**
     * 设置字体图转换为图片
     * @param tv
     * @param id
     */
    private void setTextTypeFace(TextView tv, int id) {
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "fonts/font.ttf");
        TextView tvAllIcon=tv;
        tvAllIcon.setTypeface(typeface);
        tvAllIcon.setText(activity.getResources().getString(id));
        tvAllIcon.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        tvAllIcon.setTextSize(activity.getResources().getDimension(R.dimen.sp_13));
    }

}
