package com.triforce.malacprodavac.domain.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.UUID

fun getRealPathFromUri(context: Context, contentUri: Uri): String? {
    val cursor = context.contentResolver.query(contentUri, null, null, null, null)
    val proj = { MediaStore.Images.Media.DATA }
    val index = cursor?.getColumnIndex(MediaStore.Images.Media.DATA)
    cursor?.moveToFirst()

    return cursor?.getString(index!!)
}

/**
 * Get the extension of the file the provided uri points to
 **/
fun getFileType(contentResolver: ContentResolver, uri: Uri): String? {
    val mimeTypeMap = MimeTypeMap.getSingleton()
    return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
}


/**
 * Copy contents from input stream to file
 **/
@Throws(IOException::class)
fun copyInputStreamToFile(inputStream: InputStream, file: File) {
    try {
        FileOutputStream(file, false).use { outputStream ->
            var read: Int
            val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
            while (inputStream.read(bytes).also { read = it } != -1) {
                outputStream.write(bytes, 0, read)
            }
        }
    } catch (e: IOException) {
        Log.e("Failed to load file: ", e.message.toString())
    }
}

suspend fun compressedFileFromUri(context: Context, uri: Uri): File {
    val stream = context.contentResolver.openInputStream(uri)
    val mimetype = getFileType(context.contentResolver, uri)
    val file = withContext(Dispatchers.IO) {
        return@withContext File.createTempFile(
            UUID.randomUUID().toString(),
            mimetype
        )
    }
    copyInputStreamToFile(stream!!, file)

    withContext(Dispatchers.IO) {
        stream.close()
    }
    uri.path
    val source =
        ImageDecoder.createSource(context.contentResolver, file.toUri())
    val bitmap = ImageDecoder.decodeBitmap(source)
    withContext(Dispatchers.IO) {
        file.createNewFile()
    }

    val bos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bos)
    val data = bos.toByteArray()
    withContext(Dispatchers.IO) {
        file.outputStream().write(data)
    }
    return file
}