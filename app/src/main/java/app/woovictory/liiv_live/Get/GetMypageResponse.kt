package app.woovictory.liiv_live.Get

data class GetMypageResponse (
        val msg: String,
        val data: GetMypageData
)

data class GetMypageData (
        val id: String,
        val nickname: String,
        val img: String
)
