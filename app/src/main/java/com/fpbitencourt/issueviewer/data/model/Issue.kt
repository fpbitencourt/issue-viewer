package com.fpbitencourt.issueviewer.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Issue(
    val id: Int,
    val title: String,
    val body: String?,
    val state: String,
    val url: String,
    val user: User,
    @SerializedName("created_at")
    val createdAt: String
) : Parcelable {

    companion object {
        const val STATE_OPEN = "open"
        const val STATE_CLOSED = "closed"
    }
}