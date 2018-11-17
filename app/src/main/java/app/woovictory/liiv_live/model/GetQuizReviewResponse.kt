package app.woovictory.liiv_live.model

//data class GetQuizReviewResponse (
//    val date: String,
//    val quizData: ArrayList<QuizData>
//)
//
//data class QuizData (
//    val quizId: Int,
//    val isRight: Int,
//    val example: ArrayList<ExampleData>,
//    val question: String,
//    val myAnswer: String,
//    val myAnswerID: Int,
//    val AnswerID: Int
//)
//
//data class ExampleData (
//    val id: Int,
//    val quiz_id: Int,
//    val content: String,
//    val answer_flag: Int,
//    val select_people: Int
//)
data class GetQuizReviewResponse (
    val data: ArrayList<ContestReviewData>
)

data class ContestReviewData (
    val date: String,
    val quizData: ArrayList<QuizData>
)

data class QuizData (
    val quizId: Int,
    val isRight: Int,
    val example: ArrayList<ExampleData>,
    val question: String,
    val myAnswer: String,
    val myAnswerID: Int,
    val AnswerID: Int
)

data class ExampleData (
    val id: Int,
    val quiz_id: Int,
    val content: String,
    val answer_flag: Int,
    val select_people: Int
)
