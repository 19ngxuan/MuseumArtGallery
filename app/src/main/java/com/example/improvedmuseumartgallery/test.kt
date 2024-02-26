package com.example.improvedmuseumartgallery

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    coroutineScope {

        val job = launch {
            repeat(5) {
                println("Hello $it")
                delay(500)
            }
        }
        delay(1000)
        job.join()
        println("Done!")
    }
}