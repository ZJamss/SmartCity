package com.zjamss.smartcity2.ui.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.NewsDTO;
import com.zjamss.smartcity2.http.dto.NewsTagDTO;
import com.zjamss.smartcity2.http.http;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-10 16:45
 **/
public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsTagDTO> newsTagLiveData;
    private MutableLiveData<NewsDTO> newsLiveData;

    public MutableLiveData<NewsTagDTO> getNewsTags(){
        if(newsTagLiveData == null){
            newsTagLiveData = new MutableLiveData<>();
            loadNewsTag();
        }
        return newsTagLiveData;
    }
    public MutableLiveData<NewsDTO> getNews(){
        if(newsLiveData == null){
            newsLiveData = new MutableLiveData<>();
            loadNews();
        }
        return newsLiveData;
    }

    private void loadNews() {
        http.request.getNewsList().enqueue(new CallBackImpl<NewsDTO>() {
            @Override
            public void onResponse(Call<NewsDTO> call, Response<NewsDTO> response) {
                super.onResponse(call, response);
                if(response.isSuccessful()){
                    newsLiveData.setValue(response.body());
                }
            }
        });
    }

    private void loadNewsTag() {
        http.request.getNewsTagList().enqueue(new CallBackImpl<NewsTagDTO>() {
            @Override
            public void onResponse(Call<NewsTagDTO> call, Response<NewsTagDTO> response) {
                super.onResponse(call, response);
                if(response.isSuccessful()){
                    newsTagLiveData.setValue(response.body());
                }
            }
        });
    }
}
