package com.example.chromecastvideoplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(
    var context: Context,
    var videoList: ArrayList<VideoModel>
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumb = itemView.findViewById<ImageView>(R.id.ivThumbnailList)
        var title = itemView.findViewById<TextView>(R.id.tvTitle)
        var description = itemView.findViewById<TextView>(R.id.description)
        var subTitle = itemView.findViewById<TextView>(R.id.tvSubTitle)

        fun bind(context: Context, model: VideoModel) {
            Glide.with(context)
                .load("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/" + model.thumb)
                .into(thumb)
            title.text = model.title
            description.text = model.description
            subTitle.text = model.subtitle

            thumb.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("description", description.text.toString())
                bundle.putString("imageUrl", model.thumb)
                bundle.putString("videoUrl", model.sources)
                val intent = Intent(context, VideoDetails::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = videoList[position]
        holder.bind(context, data)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}