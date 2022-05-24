package com.zjamss.smartcity2.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.FragmentUserBinding;
import com.zjamss.smartcity2.http.dto.UserInfoDTO;
import com.zjamss.smartcity2.ui.acitivty.user.FeedbackActivity;
import com.zjamss.smartcity2.ui.acitivty.user.LoginActivity;
import com.zjamss.smartcity2.ui.acitivty.user.UpdatePasswdActivity;
import com.zjamss.smartcity2.ui.acitivty.user.UserInfoActivity;
import com.zjamss.smartcity2.util.Utils;

public class UserFragment extends Fragment {


    FragmentUserBinding binding;
    SharedPreferences sp;
    UserInfoDTO.UserDTO user;
    boolean login;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = Utils.getSp(this.getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        login = sp.getBoolean(Constants.IS_LOGIN,false);
        if(login){
            Constants.TOKEN = sp.getString(Constants.GET_TOKEN,null);
            user = new Gson().fromJson(sp.getString(Constants.USER_INFO,"?"), UserInfoDTO.UserDTO.class);
            updateLoginInfo();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        binding = FragmentUserBinding.bind(view);
        eventRegister();
        return view;
    }

    private void eventRegister(){
        binding.userInfo.setOnClickListener(v->{
            if(!login){
                login();
                return;
            }
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        });
        binding.exitLogin.setOnClickListener(v->{
            if(!login){
                login();
                return;
            }
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(Constants.IS_LOGIN,false);
            editor.commit();
            this.onCreate(null);
        });
        binding.feedback.setOnClickListener(v->{
            if(!login){
                login();
                return;
            }
            Intent intent = new Intent(getActivity(), FeedbackActivity.class);
            startActivity(intent);
        });
        binding.updatePasswd.setOnClickListener(v->{
            if(!login){
                login();
                return;
            }
            Intent intent = new Intent(getActivity(), UpdatePasswdActivity.class);
            startActivity(intent);
        });
    }

    private void updateLoginInfo(){
        Constants.TOKEN = sp.getString(Constants.GET_TOKEN,null);
        user = new Gson().fromJson(sp.getString(Constants.USER_INFO,"?"), UserInfoDTO.UserDTO.class);
        Glide.with(getContext()).load(user.getAvatar()).into(binding.avatar);
        binding.username.setText(user.getUserName());
    }

    private void login(){
        if(!sp.getBoolean(Constants.IS_LOGIN,false)){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
}