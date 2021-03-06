package app.woovictory.liiv_live.db

import android.content.Context

object SharedPreferenceController {
    private val USER_NAME = "MYKEY"
    private val my_id = "my_id"
    private val my_img = "my_img"
    private val my_point = "my_point"
    private val my_fcmtoken = "my_fcmtoken"
    private val my_nickname = "my_nickname"

    fun setMyId(context: Context, id: String) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(my_id, id)
        editor.commit()
    }

    fun setMyNick(context: Context, nickname: String) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(my_nickname, nickname)
        editor.commit()
    }

    fun getMyNick(context: Context): String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(my_nickname, "홍길동")
    }

    fun getMyId(context: Context): String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(my_id, "홍길동")
    }

    fun setMyImage(context: Context, img: String) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(my_img, img)
        editor.commit()
    }

    fun getMyImage(context: Context): String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(my_img, "")
    }

    fun setMyPoint(context: Context, point: Int) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putInt(my_point, point)
        editor.commit()
    }

    fun getMyPoint(context: Context): Int {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getInt(my_point, 0)
    }

    fun setMyFcmToken(context: Context, fcmToken: String) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(my_fcmtoken, fcmToken)
        editor.commit()
    }

    fun getMyFcmToken(context: Context): String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(my_fcmtoken, "")
    }


    fun clearSPC(context: Context) {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}