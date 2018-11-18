package app.woovictory.liiv_live.Network

import app.woovictory.liiv_live.Get.*
import app.woovictory.liiv_live.Post.PostLoginResponse
import app.woovictory.liiv_live.Post.PostRefreshFcmTokenResponse
import app.woovictory.liiv_live.Post.PostSignUpResponse
import app.woovictory.liiv_live.model.GetQuizReviewResponse
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

//    // 퀴즈 리뷰 보기
//    @GET("quizreview/{userID}")
//    fun getQuizReview(
//        @Path("userID") userID: String
//    ): Call<GetQuizReviewResponse>

    // 문제 답 보기
    @GET("quizreview/answer/{quizID}")
    fun getQuizAnswerReview(
        @Path("quizID") quizID: Int
    ): Call<GetQuizAnswerReviewResponse>

    //문제 리뷰
    @GET("quizreview/{userID}")
    fun getQuizReview(
        @Path("userID") userID: String
    ): Call<GetQuizReviewResponse>

    @GET("youtube/")
    fun getYoutubeUrl(

    ) : Call<getYoutubeResponse>
}