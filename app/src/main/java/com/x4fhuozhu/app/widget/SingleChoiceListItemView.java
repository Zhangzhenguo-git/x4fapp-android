package com.x4fhuozhu.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.x4fhuozhu.app.R;

public class SingleChoiceListItemView extends LinearLayout implements Checkable {
    private TextView nameTxt;
    private CheckBox selectedCheckBox;

    public SingleChoiceListItemView(Context context) {
        super(context);
        init();
    }

    public SingleChoiceListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleChoiceListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.widget_list_single_choice, this, true);
        nameTxt = (TextView) root.findViewById(R.id.txt);
        selectedCheckBox = (CheckBox) root.findViewById(R.id.checkbox);
    }

    public TextView getNameView() {
        return nameTxt;
    }

    public void setName(String text) {
        nameTxt.setText(text);
    }

    @Override
    public boolean isChecked() {
        return selectedCheckBox.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        selectedCheckBox.setChecked(checked);
        //根据是否选中来选择不同的背景图片
        if (checked) {
            nameTxt.setTextColor(getResources().getColor(R.color.app_color_blue));
        } else {
            nameTxt.setTextColor(getResources().getColor(R.color.app_color_blue_2_disabled));
        }
    }

    @Override
    public void toggle() {
        selectedCheckBox.toggle();
    }

}