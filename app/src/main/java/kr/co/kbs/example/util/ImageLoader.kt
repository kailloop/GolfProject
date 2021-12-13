package kr.co.kbs.example.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL

object ImageLoader{

    private suspend fun loadImage(imageUrl:String): Bitmap? {
        val bmp: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val stream = url.openStream()

            return BitmapFactory.decodeStream(stream)
        }catch (e: Exception) {
            e.printStackTrace()
        }

        return bmp
    }

    fun getBitmap(url:String):Bitmap? {
        var bitmap:Bitmap? = null

        CoroutineScope(Dispatchers.Main).launch {
            launch {
                bitmap = withContext(Dispatchers.IO) {
                    this@ImageLoader.loadImage(url)
                }
            }.join()

            launch {
                withContext(Dispatchers.Default) {
                    bitmap = bitmap?.let { Bitmap.createScaledBitmap(it, 300, 200, true) }
                }
            }
        }

        return bitmap
    }

    fun bindInImageView(imageView:ImageView, url:String) {
        var bitmap:Bitmap? = null

        CoroutineScope(Dispatchers.Main).launch {
            launch {
                bitmap = withContext(Dispatchers.IO) {
                    this@ImageLoader.loadImage(url)
                }
            }.join()

            launch {
                withContext(Dispatchers.Default) {
                    bitmap = bitmap?.let { Bitmap.createScaledBitmap(it, 300, 200, true) }
                }
            }.join()

            imageView.setImageBitmap(bitmap)
        }
    }
}