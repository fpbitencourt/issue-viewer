package com.fpbitencourt.issueviewer.di

import com.fpbitencourt.issueviewer.data.repository.IssueRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single { OkHttpClient() }
    single { IssueRepository(get()) }
}
