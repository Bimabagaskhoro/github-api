package com.bimabagaskhoro.asigmentkompas.data.source.remote.response

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

data class ResponseUser(

    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<ItemsUser>
)

data class ResponseRepos(
    val responseRepos: List<ItemRepos>
)

data class ItemsUser(

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    )

data class ItemsDetail(

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("name")
    val name: String? = null

)

@Parcelize
data class ItemRepos(

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("name")
    val name: String

) : Parcelable



