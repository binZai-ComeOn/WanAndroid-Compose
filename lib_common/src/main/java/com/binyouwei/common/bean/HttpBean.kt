package com.binyouwei.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ListWrapper<T>(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: List<T>
)

data class ShareWrapper<T>(
    val coinInfo: CoinInfo,
    val shareArticles: ListWrapper<T>
)

@Parcelize
data class HotKeyBean(
    var id: Int,
    var link: String = "",
    var name: String,
    var order: Int,
    var visible: Int
) : Parcelable

@Parcelize
data class ArticleBean(
    val adminAdd: Boolean,
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    var chapterName: String? = null,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val isAdminAdd: Boolean,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    var superChapterName: String? = null,
    var tags: MutableList<Tag>? = null,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    var top: String = ""
) : Parcelable

@Parcelize
data class Tag(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class WebData(
    var title: String,
    var url: String
) : Parcelable

@Parcelize
data class RankingListBean(
    val coinCount: Int,
    val level: Int,
    val nickname: String,
    val rank: String,
    val userId: Int,
    val username: String
) : Parcelable

@Parcelize
data class ScoreBean(
    val coinCount: Int,
    val date: Long,
    val desc: String,
    val id: Int,
    val reason: String,
    val type: Int,
    val userId: Int,
    val userName: String
) : Parcelable

@Parcelize
data class KnowledgeSystemBean(
    var children: MutableList<KnowledgeSystemBean>?,
    var courseId: Int = -1,
    var id: Int = -1,
    var name: String? = "分类",
    var order: Int = -1,
    var parentChapterId: Int = -1,
    var userControlSetTop: Boolean = false,
    var visible: Int = -1,
    var icon: String? = null,
    var link: String? = null
) : Parcelable

@Parcelize
data class TabBean(
    val author: String,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val type: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Parcelable

@Parcelize
data class LoginBean(
    val admin: Boolean,
    val chapterTops: List<String>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
) : Parcelable


@Parcelize
data class UserInfoBean(
    val coinInfo: CoinInfo,
    val userInfo: UserInfo,
    val collectArticleInfo: CollectArticleInfo
) : Parcelable

@Parcelize
data class CollectArticleInfo(
    val count: Int
) : Parcelable


@Parcelize
data class CoinInfo(
    val coinCount: Int,
    val level: Int,
    val nickname: String,
    val rank: String,
    val userId: Int,
    val username: String
) : Parcelable

@Parcelize
data class UserInfo(
    val admin: Boolean,
    val chapterTops: ArrayList<Int>,
    val coinCount: Int,
    val collectIds: ArrayList<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
) : Parcelable

@Parcelize
data class ShareArticleBean<out T>(
    val coinInfo: CoinInfo,
    val shareArticles: ShareArticles
) : Parcelable

@Parcelize
data class ShareArticles(
    val curPage: Int,
    val datas: MutableList<ArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
) : Parcelable

@Parcelize
data class SettingBean(val title: String, var desc: String) : Parcelable

@Parcelize
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
) : Parcelable