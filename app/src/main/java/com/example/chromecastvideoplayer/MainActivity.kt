package com.example.chromecastvideoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    private var videosList: ArrayList<VideoModel>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvListView)
        videosList = arrayListOf()
        videoAdapter = VideoAdapter(this, videosList!!)
        recyclerView.adapter = videoAdapter


        try {
            val obj = JSONObject(loadJSONFromAsset())
            val videoArray = obj.getJSONArray("categories")
            for (i in 0 until videoArray.length()) {
                val videoDetail = videoArray.getJSONObject(i)
                val videoArrayInside = videoDetail.getJSONArray("videos")
                for (j in 0 until videoArrayInside.length()) {
                    val videoDetailInside = videoArrayInside.getJSONObject(j)
                    val videoModel = VideoModel()
                    videoModel.description = videoDetailInside.getString("description")
                    videoModel.subtitle = videoDetailInside.getString("subtitle")
                    videoModel.thumb = videoDetailInside.getString("thumb")
                    videoModel.title = videoDetailInside.getString("title")
                    videoModel.sources = videoDetailInside.getString("sources")
                    videosList?.add(videoModel)
                }
                //Log.d("onCreate: ", videosList.toString())

                videoAdapter.notifyDataSetChanged()

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String? {
        val json: String?
        try {
            val inputStream = assets.open("movies_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}