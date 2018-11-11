package app.woovictory.liiv_live.view.pointree

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.PointreeHistoryAdapter
import app.woovictory.liiv_live.model.PointreeHistoryData
import kotlinx.android.synthetic.main.activity_pointree_history.*

class PointreeHistoryActivity : AppCompatActivity() {

    lateinit var pointreeHistoryAdapter: PointreeHistoryAdapter
    lateinit var item_list : ArrayList<PointreeHistoryData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pointree_history)

        item_list = ArrayList()

        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","차감"))
        item_list.add(PointreeHistoryData("11.06(화)","쿠폰샵","-4,500P","획득"))


        pointreeHistoryRv.setHasFixedSize(true)

        pointreeHistoryAdapter = PointreeHistoryAdapter(item_list, this)
        pointreeHistoryRv.layoutManager = LinearLayoutManager(this)
        pointreeHistoryRv.adapter = pointreeHistoryAdapter


    }
}
