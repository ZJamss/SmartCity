package com.zjamss.smartcity2.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zjamss.smartcity2.R;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-10 19:55
 **/
public class TitleBar extends LinearLayout {

    private ImageView back;
    private TextView title;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(this.getContext()).inflate(R.layout.title_bar, this);

        back = findViewById(R.id.back);
        title = findViewById(R.id.title);

        back.setOnClickListener(v -> {
            ((Activity) this.getContext()).finish();
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
