package com.zjamss.smartcity2.ui.welcome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.ui.MainActivity;
import com.zjamss.smartcity2.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager vp;
    private Button dialog;
    private Button enter;
    private List<View> views = new ArrayList<>();
    private List<View> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        vp = findViewById(R.id.vp);
        //初始化页面和点
        points.add(findViewById(R.id.c1));
        points.add(findViewById(R.id.c2));
        points.add(findViewById(R.id.c3));
        views.add(LayoutInflater.from(this).inflate(R.layout.welcome1, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.welcome2, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.welcome3, null));

        updatePoints(0);
        vp.setAdapter(new WelcomeAdapter(views));
        //设置移动事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePoints(position);
                //当最后一个页面时初始化控件
                if (position == 2) {
                    dialog = findViewById(R.id.dialog_button);
                    enter = findViewById(R.id.enter_button);
                    enter.setOnClickListener(v->{
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        finish();
                    });
                    dialog.setOnClickListener(v -> {
                        showDialog();
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }


    //更新点
    void updatePoints(int p) {
        for (int i = 0; i < points.size(); i++) {
            View view = points.get(i);
            if (i == p) view.setBackgroundResource(R.drawable.shape_circle_selected);
            else view.setBackgroundResource(R.drawable.shape_circle);

            int finalI = i;
            view.setOnClickListener(v -> {
                vp.setCurrentItem(finalI);
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = Utils.getSp(this).edit();
        edit.putBoolean("isFirst",false);
        edit.commit();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.dialog_config, null);
        //设置对话框布局
        dialog.setView(dialogView);
        EditText ip = (EditText) dialogView.findViewById(R.id.ip);
        EditText port = (EditText) dialogView.findViewById(R.id.port);
        Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String edit_ip = ip.getText().toString();
                final String edit_port = port.getText().toString();
                if (TextUtils.isEmpty(edit_ip) && TextUtils.isEmpty(edit_port)) {
                    Toast.makeText(WelcomeActivity.this, "用户名和密码均不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor edit = Utils.getSp(WelcomeActivity.this).edit();
                edit.putString("ip",edit_ip);
                edit.putString("port",edit_port);
                edit.commit();
                dialog.dismiss();
                return;
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}