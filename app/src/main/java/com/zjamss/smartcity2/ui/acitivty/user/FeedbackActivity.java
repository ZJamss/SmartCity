package com.zjamss.smartcity2.ui.acitivty.user;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.zjamss.smartcity2.databinding.ActivityFeedbackBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityFeedbackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleBar.setTitle("意见反馈");

        binding.commit.setOnClickListener(v->{
            String title = binding.feedbackTitle.getText().toString();
            String content = binding.feedbackContent.getText().toString();

            JSONObject object = new JSONObject();
            try {
                object.put("title",title);
                object.put("content",content);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(MediaType.parse("application/json"),object.toString());
            http.request.feedback(body).enqueue(new CallBackImpl<BasicDTO>() {
                @Override
                public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                    super.onResponse(call, response);
                    if(response.isSuccessful()){
                        Toast.makeText(FeedbackActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

}