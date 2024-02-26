package com.example.improvedmuseumartgallery.data.network.okHttp

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.IOException
import javax.inject.Inject

class OkHttpFileDownloader @Inject constructor(private val client: OkHttpClient) : FileDownloader {
    override suspend fun downloadFile(
        url: String,
        file: File
    ): Result<Unit> = suspendCancellableCoroutine { continuation ->
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (continuation.isCancelled) return
                continuation.resumeWith(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    continuation.resumeWith(Result.failure(IOException("Failed to download file: $response")))
                    return
                }

                val responseBody = response.body
                if (responseBody == null) {
                    continuation.resumeWith(Result.failure(IOException("Response body is null")))
                    return
                }
                try {
                    if (!file.exists()) {
                        file.outputStream().use { fileOutputStream ->
                            responseBody.byteStream().copyTo(fileOutputStream)

                        }
                    }
                    continuation.resumeWith(Result.success(Result.success(Unit)))
                } catch (e: IOException) {
                    if (continuation.isCancelled) return
                    continuation.resumeWith(Result.failure(e))
                }

            }
        }
        )

        continuation.invokeOnCancellation {
            try {
                file.delete()
            } catch (_: Exception) {
            }

        }
    }
}
