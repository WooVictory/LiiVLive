package app.woovictory.liiv_live.Get

/**
 * Created by VictoryWoo
 */
data class QuestionExample(
    val id: Int,
    val quiz_id: Int,
    val content: String,
    val answer_flag: Int,
    val select_people: Int
)