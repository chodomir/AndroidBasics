package com.example.basics.d13

import android.content.ContentUris
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.basics.R
import java.lang.StringBuilder

private const val TAG = "D13MainActivity"

private data class Image(
    val uri: Uri,
    val name: String,
    val size: Int
)

class D13MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        Log.d(TAG, "Files directory: $filesDir")
        Log.d(TAG, "Cache directory: $cacheDir")
        Log.d(TAG, "Is External Storage Writable: ${isExternalStorageWritable()}")
        Log.d(TAG, "Is External Storage Readable: ${isExternalStorageReadable()}")

        val externalFiles = ContextCompat.getExternalFilesDirs(applicationContext, null)
        StringBuilder().also {
            for (file in externalFiles) it.append("${file.path}:")
            Log.d(TAG, "External files dirs: $it")
        }

        // add a dummy img
        val dummyImg = addImage("dummy_image.jpg")
        printSharedImages()
        // remove dummy img
        if (dummyImg != null) {
            deleteImage(dummyImg)
            Log.d(TAG, "After delete operation")
            printSharedImages()
        }
    }

    private fun printSharedImages() {
        val imgList = getSharedImages()
        for (img in imgList) {
            Log.d(TAG, "Image: ${img.uri}")
        }
    }

    private fun addImage(name: String) : Uri? {
        val img = ContentValues().apply() {
            put(MediaStore.Images.Media.DISPLAY_NAME, name)
        }
        return contentResolver.insert(getSharedImagesURI(), img)
    }

    private fun deleteImage(imgUriWithId: Uri): Int { val uriStr = imgUriWithId.toString()
        val index = uriStr.lastIndexOf('/')
        val id = uriStr.substring(index + 1)

        return contentResolver.delete(
                getSharedImagesURI(),
                "${MediaStore.Images.Media._ID} == $id",
                arrayOf()
                )
    }

    private fun getSharedImages() : MutableList<Image> {
        val imgList = mutableListOf<Image>()
        val collection = getSharedImagesURI()
        val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
        )

        val query = contentResolver.query(
                collection,
                projection,
                "",
                arrayOf(),
                ""
        )
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        id
                )

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                imgList += Image(contentUri, name, size)
            }
        }

        return imgList
    }

    private fun getSharedImagesURI(): Uri {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}