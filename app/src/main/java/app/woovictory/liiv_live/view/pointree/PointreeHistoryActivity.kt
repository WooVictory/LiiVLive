package app.woovictory.liiv_live.view.pointree

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.PointreeHistoryAdapter
import app.woovictory.liiv_live.db.PointreeHistoryData
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import kotlinx.android.synthetic.main.activity_pointree_history.*

class PointreeHistoryActivity : AppCompatActivity() {

    lateinit var pointreeHistoryAdapter: PointreeHistoryAdapter
    lateinit var item_list : ArrayList<PointreeHistoryData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pointree_history)

        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        pointItemDataList.addPointItemData("쿠폰샵", -600, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 600)
        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        pointItemDataList.addPointItemData("쿠폰샵", -400, "차감")
        SharedPreferenceController.setMyPoint(applicationContext,SharedPreferenceController.getMyPoint(applicationContext) - 400)
        point_history_act_point_tv.text = SharedPreferenceController.getMyPoint(applicationContext).toString()

        item_list = pointItemDataList.getPointItemData()

        pointreeHistoryRv.setHasFixedSize(true)

        pointreeHistoryAdapter = PointreeHistoryAdapter(item_list, this)
        pointreeHistoryRv.layoutManager = LinearLayoutManager(this)
        pointreeHistoryRv.adapter = pointreeHistoryAdapter


    }
}
