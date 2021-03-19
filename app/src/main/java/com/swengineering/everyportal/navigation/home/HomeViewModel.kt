package com.swengineering.everyportal.navigation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swengineering.everyportal.model.Ranking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class HomeViewModel : ViewModel() {
    var naverRanking: MutableLiveData<ArrayList<Ranking>> = MutableLiveData()
    var tab: MutableLiveData<String> = MutableLiveData()
    fun getNaverRankings() {
        // 코루틴으로 스레드 종류 변경
        GlobalScope.launch(Dispatchers.IO) {
            // 네이버 랭킹 크롤링
            val doc =
                Jsoup.connect("https://datalab.naver.com/keyword/realtimeList.naver").get()
            val elements: Elements =
                doc.select("ul.ranking_list > li.ranking_item > div.item_box")
            val rankings: ArrayList<Ranking> = arrayListOf()
            elements.forEach { e ->
                val ranking: Ranking = Ranking(
                    id = e.select("span.item_num")[0].html().toLong(),
                    title = e.select("span.item_title")[0].html().toString()
                )
                rankings.add(ranking)
            }
            launch(Dispatchers.Main) {
                println("updated naver ranking: $rankings")
                naverRanking.value = rankings
            }
        }
    }
}