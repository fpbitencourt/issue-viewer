package com.fpbitencourt.issueviewer.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable