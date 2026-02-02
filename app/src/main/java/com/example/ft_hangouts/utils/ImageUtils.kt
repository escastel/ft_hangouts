package com.example.ft_hangouts.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object ImageUtils {

    fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val fileName = "contact_${System.currentTimeMillis()}.jpg"

            val file = File(context.filesDir, fileName)
            val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            correctOrientation(file.absolutePath)

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun correctOrientation(path: String) {
        try {
            val ei = ExifInterface(path)
            val orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            val rotationAngle = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }

            if (rotationAngle != 0) {
                val bitmap = BitmapFactory.decodeFile(path) ?: return

                val matrix = Matrix()
                matrix.postRotate(rotationAngle.toFloat())

                val rotatedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                )

                FileOutputStream(path).use { out ->
                    rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }

                bitmap.recycle()
                if (rotatedBitmap != bitmap) {
                    rotatedBitmap.recycle()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}