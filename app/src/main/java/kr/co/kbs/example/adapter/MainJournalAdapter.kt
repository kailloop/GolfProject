package kr.co.kbs.example.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kr.co.kbs.example.databinding.ListMainJournalBinding

class MainJournalAdapter(private val context:Context, var items: ArrayList<Int>) : RecyclerView.Adapter<MainJournalAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListMainJournalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding:ListMainJournalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataLst: List<Int>, position:Int) {

            // binding.imgJournal.setImageDrawable(dataLst.get(position));
            binding.imgJournal.setImageResource(dataLst.get(position))


//            var picture: Bitmap = BitmapFactory.decodeResource(context.resources, dataLst.get(position))
//            binding.imgJournal.scaleType = ImageView.ScaleType.FIT_CENTER
//            binding.imgJournal.setImageBitmap(picture)


        }
    }
}