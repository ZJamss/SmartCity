package com.zjamss.smartcity2.ui.recommendedService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.RecommendedServiceDTO;
import com.zjamss.smartcity2.http.http;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-10 09:56
 **/
public class RecommendedServiceViewModel extends ViewModel {

    private MutableLiveData<RecommendedServiceDTO> recommendedServiceDTOLiveData;

    //export
    public MutableLiveData<RecommendedServiceDTO> getRecommendedService() {
        if (recommendedServiceDTOLiveData == null) {
            recommendedServiceDTOLiveData = new MutableLiveData<RecommendedServiceDTO>();
            load();
        }
        return recommendedServiceDTOLiveData;
    }

    //加载服务
    private void load() {
        http.request.getRecommendedServiceList().enqueue(new CallBackImpl<RecommendedServiceDTO>() {
            @Override
            public void onResponse(Call<RecommendedServiceDTO> call, Response<RecommendedServiceDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    recommendedServiceDTOLiveData.setValue(response.body());
                }
            }
        });
    }
}
