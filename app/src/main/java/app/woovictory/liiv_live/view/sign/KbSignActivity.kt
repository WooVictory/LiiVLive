package app.woovictory.liiv_live.view.sign

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.Post.PostSignUpResponse
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.view.login.LoginActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_kb_sign.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class KbSignActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            kbSignImage->{
                requestReadExternalStoragePermission()
            }
        }
    }

    val MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE: Int = 2001
    val REQUEST_CODE_SELECT_IMAGE: Int = 2002

    lateinit var networkService: NetworkService
    private var img: MultipartBody.Part? = null
    private val REQ_CODE_SELECT_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kb_sign)

        setOnClickListener()

        //setOnClickListener()

        kbSignId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 && kbSignNickname.text.toString().length == 0) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.viewColor),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (kbSignId.text.toString().length > 1 && kbSignPw.text.toString().length > 1
                        && kbSignNickname.text.toString().length > 1) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.greyblue),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })
        kbSignPw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 &&
                        kbSignNickname.text.toString().length == 0) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.viewColor),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (kbSignId.text.toString().length > 1 && kbSignPw.text.toString().length > 1
                        && kbSignNickname.text.toString().length > 1) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.greyblue),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })
        kbSignNickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 &&
                        kbSignNickname.text.toString().length == 0) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.viewColor),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (kbSignId.text.toString().length > 1 && kbSignPw.text.toString().length > 1
                        && kbSignNickname.text.toString().length > 1) {
                    kbSignBtn.background.setColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.greyblue),
                            PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })
    }

    fun postReviewWrite() {
        networkService = ApplicationController.instance.networkService
        var id = RequestBody.create(MediaType.parse("text/plain"), kbSignId.text.toString())
        var pw = RequestBody.create(MediaType.parse("text/plain"), kbSignPw.text.toString())
        var nickname = RequestBody.create(MediaType.parse("text/plain"), kbSignNickname.text.toString())

        if (img == null) {
            val postSignUpResponse = networkService.postSignUpResponse(id, pw, nickname, null)
            postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
                override fun onFailure(call: Call<PostSignUpResponse>?, t: Throwable?) {
                    toast("회원가입 실패")
                }

                override fun onResponse(call: Call<PostSignUpResponse>?, response: Response<PostSignUpResponse>?) {
                    if (response!!.isSuccessful) {
                        if (response!!.body()!!.message == "존재하는 아이디 있음"){
                            toast("존재하는 아이디 있음")

                        } else {
                            toast("회원가입 성공")
                            SharedPreferenceController.setMyNick(this@KbSignActivity, kbSignNickname.text.toString())
                            startActivity<LoginActivity>()
                        }
                    }
                }
            })
        } else {
            val postSignUpResponse = networkService.postSignUpResponse(id, pw, nickname, img)
            postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
                override fun onFailure(call: Call<PostSignUpResponse>?, t: Throwable?) {
                    toast("회원가입 실패")
                }

                override fun onResponse(call: Call<PostSignUpResponse>?, response: Response<PostSignUpResponse>?) {
                    if (response!!.isSuccessful) {
                        if (response!!.body()!!.message == "존재하는 아이디 있음"){
                            toast("존재하는 아이디 있음")

                        } else {
                            toast("회원가입 성공")
                            SharedPreferenceController.setMyNick(this@KbSignActivity, kbSignNickname.text.toString())
                            startActivity<LoginActivity>()
                        }
                    }
                }
            })
        }

    }

    // 이미지 뷰 바꾸고 클릭 됐을 때 이미지 뷰 선택 창으로 이동
    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    private fun requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE)
            }
        } else {
            changeImage()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                    intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
                } else {
                    requestReadExternalStoragePermission()
                }
                return
            }
        }
    }

    private fun setOnClickListener() {
        kbSignImage.setOnClickListener {
            requestReadExternalStoragePermission()
        }

        kbSignBtn.setOnClickListener {
            postReviewWrite()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    var  seletedPictureUri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream : InputStream = contentResolver.openInputStream(seletedPictureUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

                    img = MultipartBody.Part.createFormData("img", File(seletedPictureUri.toString()).name, photoBody)

                    Glide.with(this@KbSignActivity).load(seletedPictureUri).thumbnail(0.1f).into(kbSignImage)
                }
            }
        }
    }


}
