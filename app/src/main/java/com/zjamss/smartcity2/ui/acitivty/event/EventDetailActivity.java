package com.zjamss.smartcity2.ui.acitivty.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityEventDetailBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.ActivityCommentDTO;
import com.zjamss.smartcity2.http.dto.ActivityDTO;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {

    ActivityEventDetailBinding binding;
    ActivityDTO.RowsDTO activity;

    public static void actionStart(Context context, ActivityDTO.RowsDTO activity) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ACTIVITY_INFO, activity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activity = (ActivityDTO.RowsDTO) bundle.getSerializable(Constants.ACTIVITY_INFO);

        updateBasicInfo();
        updateCommentList();
        registerEvent();
        updateRecommendedActivities();
    }

    private void updateRecommendedActivities() {
        http.request.getActivities("Y").enqueue(new CallBackImpl<ActivityDTO>() {
            @Override
            public void onResponse(Call<ActivityDTO> call, Response<ActivityDTO> response) {
                super.onResponse(call, response);
                if (response.body().getCode() == 200) {
                    binding.recommendedActivities.setAdapter(new ActivityAdapter(response.body().getRows()));
                    binding.recommendedActivities.setLayoutManager(new LinearLayoutManager(EventDetailActivity.this));
                }
            }
        });
    }

    private void registerEvent() {
        binding.commitComment.setOnClickListener(v -> {
            if (!Utils.isLogin(this)) {
                Utils.login(this);
                return;
            }
            JSONObject object = new JSONObject();
            try {
                object.put("activityId", activity.getId());
                object.put("content", binding.commentContent.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            http.request.addComment(Constants.TOKEN, RequestBody.create(MediaType.parse("application/json"), object.toString()))
                    .enqueue(new CallBackImpl<BasicDTO>() {
                        @Override
                        public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                            super.onResponse(call, response);
                            if (response.body().getCode() == 200) {
                                Toast.makeText(EventDetailActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EventDetailActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        binding.signUp.setOnClickListener(v -> {
            if (!Utils.isLogin(this)) {
                Utils.login(this);
                return;
            }
            JSONObject object = new JSONObject();
            try {
                object.put("activityId", activity.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            http.request.signUpActivity(Constants.TOKEN, RequestBody.create(MediaType.parse("application/json"), object.toString()))
                    .enqueue(new CallBackImpl<BasicDTO>() {
                        @Override
                        public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                            super.onResponse(call, response);
                            if (response.body().getCode() == 200) {
                                Toast.makeText(EventDetailActivity.this, "参加成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EventDetailActivity.this, "参加失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    private void updateCommentList() {
        http.request.getActivityCommentById(activity.getId()).enqueue(new CallBackImpl<ActivityCommentDTO>() {
            @Override
            public void onResponse(Call<ActivityCommentDTO> call, Response<ActivityCommentDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    List<ActivityCommentDTO.RowsDTO> comments = response.body().getRows();
                    binding.commentNum.setText(comments.size() + "");
                    binding.comments.setAdapter(new CommentAdapter(comments));
                    binding.comments.setLayoutManager(new LinearLayoutManager(EventDetailActivity.this));
                }
            }
        });
    }


    void updateBasicInfo() {
        binding.titleBar.setTitle(activity.getName());
        Glide.with(binding.image).load(Constants.BASE_URL + activity.getImgUrl()).into(binding.image);
        binding.content.loadData(Html.fromHtml(activity.getContent(), Html.FROM_HTML_MODE_LEGACY).toString(), "text/html", "UTF-8");
    }


}