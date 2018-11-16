package app.woovictory.liiv_live.db

import java.text.SimpleDateFormat
import java.util.*

object pointItemDataList {
    var dataList : ArrayList<PointreeHistoryData> = ArrayList()
    var level : String = ""

    fun setLevel()

    fun addPointItemData(history_type: String, history_point: Int, history_method: String){

        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        dataList.add(PointreeHistoryData(getTime, history_type, history_point, history_method))
    }

    fun getPointItemData() : ArrayList<PointreeHistoryData> {
        return dataList
    }
}

data class PointreeHistoryData (
        var history_date : String,
        var history_type : String,
        var history_point : Int,
        var history_method : String
)