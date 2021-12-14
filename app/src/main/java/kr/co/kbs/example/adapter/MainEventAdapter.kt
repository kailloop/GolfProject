package kr.co.kbs.example.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.kbs.example.R
import kr.co.kbs.example.databinding.ListMainEventBinding
import kr.co.kbs.example.ui.activity.MainActivity
import kr.co.kbs.example.util.ImageLoader
import kr.co.kbs.example.util.Util
import java.util.*
import kotlin.collections.ArrayList

class MainEventAdapter(
    private val context: Context,
    private val activity: MainActivity,
    var items: ArrayList<String> = arrayListOf()
) :

    RecyclerView.Adapter<MainEventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEventAdapter.ViewHolder {
        val binding = ListMainEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: ListMainEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataLst: List<String>, position: Int) {

            CoroutineScope(Dispatchers.Main).launch {
                launch {
                    withContext(Dispatchers.IO){
                        ImageLoader.bindInImageView(binding.imgEvent, dataLst.get(position))
                    }
                }.join()

                launch {
                    if (position != (dataLst.size - 1)) {
                        activity.toggleProgress(activity.binding.pgEvent, false)
                    }
                }.join()
            }
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    fun swapData(fromPos: Int, toPos: Int) {
        notifyItemMoved(fromPos, toPos)
    }

    fun removeData(position: Int) {
        notifyItemRemoved(position)
    }
}