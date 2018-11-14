package app.woovictory.liiv_live.Network

import app.woovictory.liiv_live.Post.PostSignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkService {

    @Multipart
    @POST("signup/")
    fun postSignUpResponse(
            @Part("id") id: RequestBody,
            @Part("pw") pw : RequestBody,
            @Part("nickname") nickname : RequestBody,
            @Part img: MultipartBody.Part?
    ): Call<PostSignUpResponse>
}