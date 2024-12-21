package com.example.lop124lttd03.nhom_t_d_p.Service;

import com.example.lop124lttd03.nhom_t_d_p.Model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("signup.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
            @Field("email") String email,
            @Field("userPassWord") String passWord,
            @Field("userName") String userName // Mấy cái String trong Field là phải giống mấy cái tên trường trong database ae nghe
    );

    @POST("login.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("userPassWord") String passWord
            // Mấy cái String trong Field là phải giống mấy cái tên trường trong database ae nghe
    );


}