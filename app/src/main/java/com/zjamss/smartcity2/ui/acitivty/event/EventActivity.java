package com.zjamss.smartcity2.ui.acitivty.event;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.youth.banner.indicator.CircleIndicator;
import com.zjamss.smartcity2.databinding.ActivityEventBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.ActivityDTO;
import com.zjamss.smartcity2.http.dto.ActivityTagDTO;
import com.zjamss.smartcity2.http.dto.BannerDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.ui.index.BannerAdapterImpl;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;
    List<ActivityDTO.RowsDTO> activities;
    List<ActivityDTO.RowsDTO> currentActivities;
    ActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleBar.setTitle("活动列表");
        updateBanner();
        updateTags();
        updateActivities();
    }

    private void updateActivities() {
        http.request.getActivities("N").enqueue(new CallBackImpl<ActivityDTO>() {
            @Override
            public void onResponse(Call<ActivityDTO> call, Response<ActivityDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    activities = response.body().getRows();
                    currentActivities =
                            activities.stream()
                                    .filter((ActivityDTO.RowsDTO dto) -> dto.getCategoryId() == 1)
                                    .collect(Collectors.toList());
                    adapter = new ActivityAdapter(currentActivities);
                    binding.activities.setAdapter(adapter);
                    binding.activities.setLayoutManager(new LinearLayoutManager(EventActivity.this));
                }
            }
        });
    }

    void updateBanner() {
        http.request.getActivityBanner().enqueue(new CallBackImpl<BannerDTO>() {
            @Override
            public void onResponse(Call<BannerDTO> call, Response<BannerDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    List<BannerDTO.RowsDTO> rows = response.body().getRows();
                    binding.banner.addBannerLifecycleObserver(EventActivity.this)
                            .setAdapter(new BannerAdapterImpl(rows))
                            .setIndicator(new CircleIndicator(EventActivity.this));
                }
            }
        });
    }

    void updateTags() {
        http.request.getActivityTags().enqueue(new CallBackImpl<ActivityTagDTO>() {
            @Override
            public void onResponse(Call<ActivityTagDTO> call, Response<ActivityTagDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    for (ActivityTagDTO.DataDTO tag : response.body().getData()) {
                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(tag.getName()));
                    }
                    registerEvent();
                }
            }
        });
    }

    void registerEvent(){
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int id = tab.getPosition();
                currentActivities.clear();
                currentActivities.addAll(activities.stream().filter((ActivityDTO.RowsDTO dto) -> dto.getCategoryId() == id+1).collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}