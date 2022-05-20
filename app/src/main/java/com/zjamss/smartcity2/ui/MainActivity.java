package com.zjamss.smartcity2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityMainBinding;
import com.zjamss.smartcity2.ui.index.IndexFragment;
import com.zjamss.smartcity2.ui.news.NewsFragment;
import com.zjamss.smartcity2.ui.recommendedService.ServiceFragment;
import com.zjamss.smartcity2.ui.smartCommunity.SmartCommunityFragment;
import com.zjamss.smartcity2.ui.user.UserFragment;
import com.zjamss.smartcity2.ui.welcome.WelcomeActivity;
import com.zjamss.smartcity2.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context CONTEXT;

    public static ActivityMainBinding binding;
    List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否第一次启动
        if (Utils.getSp(this).getBoolean("isFirst", true)) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
            return;
        }

        if(Utils.isLogin(this)){
            Constants.TOKEN = Utils.getSp(this).getString(Constants.GET_TOKEN,null);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CONTEXT = getBaseContext();

        binding.bnv.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        binding.bnv.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Index:
                    binding.mainVp.setCurrentItem(0);
                    break;
                case R.id.allService:
                    binding.mainVp.setCurrentItem(1);
                    break;
                case R.id.SmartCommunity:
                    binding.mainVp.setCurrentItem(2);
                    break;
                case R.id.News:
                    binding.mainVp.setCurrentItem(3);
                    break;
                case R.id.PersonalCenter:
                    binding.mainVp.setCurrentItem(4);
                    break;
            }
            return true;
        });
        binding.mainVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.bnv.setSelectedItemId(binding.bnv.getMenu().getItem(position).getItemId());
            }
        });

        fragmentList.add(new IndexFragment());
        fragmentList.add(new ServiceFragment());
        fragmentList.add(SmartCommunityFragment.newInstance("test", "123"));
        fragmentList.add(new NewsFragment());
        fragmentList.add(new UserFragment());
        binding.mainVp.setAdapter(new MainViewPagerAdapter(fragmentList, getSupportFragmentManager(), getLifecycle()));
    }
}