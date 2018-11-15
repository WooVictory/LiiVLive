package app.woovictory.liiv_live.Network

import app.woovictory.liiv_live.Post.PostLoginResponse
import app.woovictory.liiv_live.Post.PostSignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @Multipart
    @POST("signup/")
    fun postSignUpResponse(
            @Part("id") id: RequestBody,
            @Part("pw") pw : RequestBody,
            @Part("nickname") nickname : RequestBody,
            @Part img: MultipartBody.Part?
    ): Call<PostSignUpResponse>

    // 로그인 하기
    @FormUrlEncoded
    @POST("login/")
    fun postLoginResponse(
            @Field("id") id: String,
            @Field("pw") pw: String
    ): Call<PostLoginResponse>
}