package com.zjamss.smartcity2.http;

import com.zjamss.smartcity2.http.dto.ActivityCommentDTO;
import com.zjamss.smartcity2.http.dto.ActivityDTO;
import com.zjamss.smartcity2.http.dto.ActivityTagDTO;
import com.zjamss.smartcity2.http.dto.BannerDTO;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.dto.HospitalListDTO;
import com.zjamss.smartcity2.http.dto.LoginDTO;
import com.zjamss.smartcity2.http.dto.NewsDTO;
import com.zjamss.smartcity2.http.dto.NewsTagDTO;
import com.zjamss.smartcity2.http.dto.OutpatientDepartment;
import com.zjamss.smartcity2.http.dto.PatientCardDTO;
import com.zjamss.smartcity2.http.dto.RecommendedServiceDTO;
import com.zjamss.smartcity2.http.dto.UserInfoDTO;
import com.zjamss.smartcity2.model.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-09 15:19
 **/
public interface Request {
    @GET("/prod-api/api/rotation/list?type=2")
    Call<BannerDTO> getBannerList();

    @GET("/prod-api/api/service/list")
    Call<RecommendedServiceDTO> getRecommendedServiceList();

    @GET("/prod-api/press/category/list")
    Call<NewsTagDTO> getNewsTagList();

    @GET("/prod-api/press/press/list")
    Call<NewsDTO> getNewsList();

    @POST("/prod-api/api/login")
    Call<LoginDTO> login(@Body RequestBody body);

    @POST("/prod-api/api/register")
    Call<BasicDTO> register(@Body User user);

    @GET("/prod-api/api/common/user/getInfo")
    Call<UserInfoDTO> getUserInfo(@Header("Authorization") String token);

    @PUT("/prod-api/api/common/user")
    Call<BasicDTO> updateUserInfo(@Header("Authorization") String token, @Body UserInfoDTO.UserDTO user);

    @POST("/prod-api/api/common/feedback")
    Call<BasicDTO> feedback(@Body RequestBody body);

    @PUT("/prod-api/api/common/user/resetPwd")
    Call<BasicDTO> updateUserPasswd(@Header("Authorization") String token,@Body RequestBody body);

    @GET("/prod-api/api/activity/rotation/list")
    Call<BannerDTO> getActivityBanner();

    @GET("/prod-api/api/activity/category/list")
    Call<ActivityTagDTO> getActivityTags();

    @GET("/prod-api/api/activity/activity/list?&pageNum=1&pageSize=999")
    Call<ActivityDTO> getActivities(@Query("recommend") String recommend);

    @GET("/prod-api/api/activity/comment/list?pageNum=1&pageSize=30")
    Call<ActivityCommentDTO> getActivityCommentById(@Query("activityId") int id);

    @POST("/prod-api/api/activity/comment")
    Call<BasicDTO> addComment(@Header("Authorization") String token, @Body RequestBody body);

    @POST("/prod-api/api/activity/signup")
    Call<BasicDTO> signUpActivity(@Header("Authorization") String token,@Body RequestBody body);

    @GET("/prod-api/api/hospital/hospital/list")
    Call<HospitalListDTO> getHospitalList();

    @GET("/prod-api/api/hospital/patient/list")
    Call<PatientCardDTO> getPatientCardList(@Header("Authorization") String token);

    @POST("/prod-api/api/hospital/patient")
    Call<BasicDTO> createPatientCard(@Header("Authorization") String token,@Body RequestBody body);

    @GET("/prod-api/api/hospital/category/list")
    Call<OutpatientDepartment> getOutpatientDepartmentList();

    @POST("/prod-api/api/hospital")
    Call<BasicDTO> book(@Header("Authorization") String token,@Body RequestBody body);
}
