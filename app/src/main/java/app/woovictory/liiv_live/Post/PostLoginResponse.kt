package app.woovictory.liiv_live.Post

data class PostLoginResponse (
        val message: String,
        val data: ArrayList<PostLoginResponseData>
)

data class PostLoginResponseData (
        val id: String,
        val nickname: String,
        val pw: String,
        val level: Int,
        val img: String,
        val sub_pw: Int
)
