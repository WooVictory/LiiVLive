package app.woovictory.liiv_live.Get

data class GetQuizResponse (
        val msg: String,
        val data: GetQuizData
)

data class GetQuizData (
        val question: Question,
        val question_example: ArrayList<QuestionExample>
)

data class Question (
        val id: Int,
        val question: String,
        val present_flag: Int,
        val contest_id: Int,
        val answer_content: String,
        val finance_flag: Int
)


