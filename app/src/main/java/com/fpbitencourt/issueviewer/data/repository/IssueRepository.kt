package com.fpbitencourt.issueviewer.data.repository

import com.fpbitencourt.issueviewer.data.model.Issue
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.component.KoinComponent

class IssueRepository(private val client: OkHttpClient) : KoinComponent {

    companion object {
        private const val REPOSITORY: String = "https://api.github.com/repos/JetBrains/kotlin/issues"
    }

    fun getIssues(url: String): Single<List<Issue>> {
        return Single.create { emitter ->
            val request = Request.Builder().url(url).build()

            try {
                val response = client.newCall(request).execute()
                val json = response.body?.string()
                val issues = parseJson(json)
                emitter.onSuccess(issues)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    fun getDefaultRepositoryUrl(): String {
        return REPOSITORY
    }

    private fun parseJson(json: String?): List<Issue> {
        return Gson().fromJson(json, Array<Issue>::class.java).toList()
    }

}