package app.woovictory.liiv_live.Network

import app.woovictory.liiv_live.Get.GetMypageResponse
import app.woovictory.liiv_live.Get.GetQuizAnswerResponse
import app.woovictory.liiv_live.Get.GetQuizResponse
import app.woovictory.liiv_live.Get.GetUserMainResponse
import app.woovictory.liiv_live.Post.PostLoginResponse
import app.woovictory.liiv_live.Post.PostRefreshFcmTokenResponse
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

    // 로그인 하기
    @FormUrlEncoded
    @POST("refreshfcmtoken/")
    fun postRefreshFcmTokenResponse(
            @Field("userID") userID: String,
            @Field("fcmToken") fcmToken: String
    ): Call<PostRefreshFcmTokenResponse>

    //카테고리 상세보기 통신
    @GET("main/{userID}")
    fun getUserMain(
            @Path("userID") userID: String
    ): Call<GetUserMainResponse>

    //카테고리 상세보기 통신
    @GET("mypage/{userID}")
    fun getMyPageMain(
            @Path("userID") userID: String
    ): Call<GetMypageResponse>

    // 문제보기
    @GET("quiz/getQuiz")
    fun getQuiz(
    ): Call<GetQuizResponse>

    // 문제 제출
    @FormUrlEncoded
    @POST("quiz/")
    fun postQuizResponse(
            @Field("quizID") quizID: String,
            @Field("quizExampleID") quizExampleID: String,
            @Field("userID") userID: String
    ): Call<GetQuizAnswerResponse>

}