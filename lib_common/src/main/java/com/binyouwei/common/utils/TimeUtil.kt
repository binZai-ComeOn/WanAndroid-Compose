package com.binyouwei.common.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtil {
    /**
     * 获取当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @return 时间
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun currentTime(): String {
        return currentTime("yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 获取当前时间
     * @param pattern 格式
     * @return 时间
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun currentTime(pattern: String): String {
        val ldt: LocalDateTime = LocalDateTime.now()
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        return ldt.format(dtf)
    }

    /**
     * 通过date获取时间 格式：yyyy-MM-dd HH:mm:ss
     * @param date 日期
     * @return 时间
     */
    fun timeOf(date: Date): String {
        return timeOf(date, "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 通过date获取时间
     * @param date    日期
     * @param pattern 格式
     * @return 时间
     */
    @SuppressLint("SimpleDateFormat")
    fun timeOf(date: Date, pattern: String): String {
        val sdf = SimpleDateFormat(pattern)
        return sdf.format(date)
    }

    /**
     * 通过时间戳获取时间 格式：yyyy-MM-dd HH:mm:ss
     * @param timeMillis 时间戳(毫秒)
     * @return  时间
     */
    fun timeOf(timeMillis: Long): String {
        return timeOf(timeMillis, "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 通过时间戳获取时间 格式：yyyy-MM-dd HH:mm:ss
     * @param timeMillis 时间戳(毫秒)
     * @param pattern    格式
     * @return  对应时间
     */
    @SuppressLint("SimpleDateFormat")
    fun timeOf(timeMillis: Long, pattern: String): String {
        val sdf = SimpleDateFormat(pattern)
        return sdf.format(timeMillis)
    }

    /**
     * 获取当前时间戳(毫秒)
     * @return 时间戳(毫秒)
     */
    fun currentTimeMillis(): Long {
        return Date().time
    }

    /**
     * 获取当前时间戳(毫秒)字符串
     * @return 时间戳(毫秒)字符串
     */
    fun currentTimeMillisStr(): String {
        return java.lang.String.valueOf(Date().time)
    }

    /**
     * 获取指定时间的时间戳(毫秒)  格式：yyyy-MM-dd HH:mm:ss
     * @param time    时间
     * @return 时间戳(毫秒)
     */
    fun timeMillisOf(time: String): Long {
        return timeMillisOf(time, "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 获取指定时间及格式的时间戳(毫秒)
     * @param time    时间
     * @param pattern 格式
     * @return 时间戳(毫秒)
     */
    @SuppressLint("SimpleDateFormat")
    fun timeMillisOf(time: String, pattern: String): Long {
        val sdf = SimpleDateFormat(pattern)
        var date: Date? = null
        try {
            date = sdf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date?.time ?: 0
    }

    /**
     * 获取指定时间的时间戳(毫秒)字符串  格式：yyyy-MM-dd HH:mm:ss
     * @param time    时间
     * @return 时间戳(毫秒)字符串
     */
    fun timeMillisStrOf(time: String): String {
        return timeMillisStrOf(time, "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 获取指定时间及格式的时间戳(毫秒)字符串
     * @param time    时间
     * @param pattern 格式
     * @return 时间戳(毫秒)字符串
     */
    fun timeMillisStrOf(time: String, pattern: String): String {
        return timeMillisOf(time, pattern).toString()
    }

    /**
     * 获取指定Date时间戳(毫秒)
     * @param date date
     * @return 时间戳(毫秒)
     */
    fun timeMillisOf(date: Date): Long {
        return date.time
    }

    /**
     * 获取指定Date时间戳(毫秒)字符串
     * @param date date
     * @return 时间戳(毫秒)字符串
     */
    fun timeMillisStrOf(date: Date): String {
        return java.lang.String.valueOf(date.time)
    }

    /**
     * 通过时间获取date对象 格式：yyyy-MM-dd HH:mm:ss
     * @param time    时间
     * @return date
     */
    fun dateOf(time: String): Date? {
        return dateOf(time, "yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 通过时间获取date对象
     * @param time    时间
     * @param pattern 格式
     * @return date
     */
    @SuppressLint("SimpleDateFormat")
    fun dateOf(time: String, pattern: String): Date? {
        val sdf = SimpleDateFormat(pattern)
        var date: Date? = null
        try {
            date = sdf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    /**
     * 通过时间戳获取date对象
     * @param timeMillis 时间戳
     * @return date
     */
    fun dateOf(timeMillis: Long): Date {
        return Date(timeMillis)
    }

}