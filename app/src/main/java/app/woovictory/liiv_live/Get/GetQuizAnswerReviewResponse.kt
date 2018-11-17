package app.woovictory.liiv_live.Get

data class GetQuizAnswerReviewResponse (
    val message: String,
    val data: ArrayList<GetQuizAnswerReviewResponseData>
)

data class GetQuizAnswerReviewResponseData (
    val content: String,
    val answer_content: String
)