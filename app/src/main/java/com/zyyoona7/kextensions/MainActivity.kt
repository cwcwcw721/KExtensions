package com.zyyoona7.kextensions

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.zyyoona7.lib.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logEnabled(true)
//        logGlobalTag("GlobalTag")
//        logv("Hello")
//        logd("Hello", customTag = "customTag")
//        logi("Hello")
//        logw("Hello")
//        loge("Hello")
//        val json = """         {'name':'zyyoona7','age':'20'}   """
//        logJson(json)
//
//        loge(fileDirPath)
//        loge(cacheDirPath)
//        loge(externalFileDirPath)
//        loge(externalCacheDirPath)
//
//        loge("dir exist ${isDirExists(externalFileDirPath)}")
//
//        loge("dir exist ${isDirExists(externalFileDirPath + "/a")}")
//
//        loge(getDirSize(publicDownloadDir))
//        loge(getDirSize(publicDCIMDir))
        loge(getFileByPath(publicPictureDir)?.dirSize ?: " ")
        loge(getFileByPath(publicPictureDir)?.dirSize ?: " ")
        loge(getFileByPath(publicPictureDir)?.dirSize ?: " ")
//        loge(getDirSize(publicMusicDir))
//        loge(getDirSize(publicMovieDir))
//
//        loge("path=$download \nDir=${getDirName(download)}")
//        loge("path=$download \nFile=${getFileName(download)}")
//

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 11223)
        } else {
            writeFile()
        }

        val cal = Calendar.getInstance()
//        cal.add(Calendar.HOUR_OF_DAY, -25)
//        loge(cal.timeInMillis.formatAgoStyleForWeChat())
//        loge(cal.timeInMillis.formatAgoStyleForWeibo())
        cal.add(Calendar.DATE, 2)
        loge("day number of week = ${cal.timeInMillis.dayOfWeek}")
    }

    private fun writeFile() {
        val download = publicDownloadDir + "/a.txt"

        val content = """a
            |a
            |a
            |a
            |s
            |d
            |d
            |f
        """.trimMargin()
        writeStringAsFile(download, content)

        loge(readFileAsString(download))
        val destFilePath = publicPictureDir + "/b.txt"
        createOrExistsFile(destFilePath)
        loge("copy finished ${copyOrMoveFile(download, destFilePath, true)}")
        installApp()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 11223) {
            writeFile()
        }
    }

    private fun installApp() {
        val file = getFileByPath("$publicDownloadDir/app-debug.apk")
        file?.let {
            installApp(file, "a", false)
        }
    }
}
