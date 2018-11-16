package app.woovictory.liiv_live.Get

data class GetUserMainResponse (
        val msg: String,
        val data: GetUserMainData
)

data class GetUserMainData (
        val id: String,
        val nickname: String,
        val level: Int,
        val img: String
)
