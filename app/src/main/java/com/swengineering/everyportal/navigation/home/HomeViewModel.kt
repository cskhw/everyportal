package com.swengineering.everyportal.navigation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swengineering.everyportal.model.Ranking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class HomeViewModel : ViewModel() {
    var naverRanking: MutableLiveData<ArrayList<Ranking>> = MutableLiveData()
    var tab: MutableLiveData<Int> = MutableLiveData()
    fun getNaverRankings() {
        // 코루틴으로 스레드 종류 변경
        GlobalScope.launch(Dispatchers.IO) {
            println("getNaverRankings()")
            // 네이버 랭킹 크롤링
            try {
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
                println("rankings: $rankings")
                launch(Dispatchers.Main) {
                    println("updated naver ranking: $rankings")
                    naverRanking.value = rankings
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getDaumRankings() {
        GlobalScope.launch(Dispatchers.IO) {
            val doc: Document =
                Jsoup.connect("https://trends.google.com/trends/trendingsearches/realtime?geo=US&category=all")
                    .get()
            val elements: Elements = doc.select("md-list.md-list-block")
            println("elements: $elements")
        }
    }

    fun getZumRankings() {

    }

    fun getGoogleRankings() {

    }
    
    fun getRankings(portalName: String) {
        when (portalName) {
            "네이버" -> getNaverRankings()
            "다음" -> getDaumRankings()
        }
    }

}