package com.fpbitencourt.issueviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpbitencourt.issueviewer.data.model.Issue
import com.fpbitencourt.issueviewer.data.repository.IssueRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class IssueListViewModel : ViewModel() {

    private val repository: IssueRepository by inject(IssueRepository::class.java)

    private val _issues = MutableLiveData<List<Issue>>()
    val issues: LiveData<List<Issue>> get() = _issues

    private val _errorEvent = MutableLiveData<String>()
    val errorEvent: LiveData<String> get() = _errorEvent

    private val _defaultRepositoryUrl = MutableLiveData<String>()
    val defaultRepositoryUrl: LiveData<String> get() = _defaultRepositoryUrl

    init {
        viewModelScope.launch {
            _defaultRepositoryUrl.value = repository.getDefaultRepositoryUrl()
        }
    }

    fun loadIssues(url: String) {
        repository.getIssues(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ issues ->
                _issues.value = issues
            }, { error ->
                handleError(error)
            })
    }

    private fun handleError(error: Throwable) {
        val errorMessage = "Error: ${error.message}"
        _errorEvent.value = errorMessage
    }
}