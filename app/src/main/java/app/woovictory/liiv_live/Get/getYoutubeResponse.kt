package app.woovictory.liiv_live.Get

/**
 * Created by VictoryWoo
 */
data class getYoutubeResponse(
    var message: String,
    var data: ArrayList<youtubeId>
)

data class youtubeId(
    var youtube_id: String

)