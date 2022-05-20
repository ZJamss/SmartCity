package com.zjamss.smartcity2.ui.acitivty.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityNewsListBinding;
import com.zjamss.smartcity2.http.dto.NewsDTO;
import com.zjamss.smartcity2.ui.news.NewsAdapter;
import com.zjamss.smartcity2.ui.news.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {
    private ActivityNewsListBinding binding;
    String keyWord;
    NewsViewModel newsViewModel;
    List<NewsDTO.RowsDTO> resultList = new ArrayList<>();
    ProgressDialog progressDialog;


    public static void actionStart(Context context,String keyWord){
        Intent intent = new Intent(context,NewsListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_WORD,keyWord);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在搜索");
        progressDialog.setCancelable(false);
        progressDialog.show();

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        Bundle bundle = getIntent().getExtras();
        keyWord = bundle.getString(Constants.KEY_WORD);



        newsViewModel.getNews().observe(this,dto->{
            for(NewsDTO.RowsDTO news :dto.getRows()){
                if(news.getTitle().contains(keyWord) || news.getContent().contains(keyWord)){
                    resultList.add(news);
                }
            }
            binding.newsList.setAdapter(new NewsAdapter(resultList));
            binding.newsList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            progressDialog.dismiss();
        });

        binding.titleBar.setTitle("新闻搜索");
    }

}