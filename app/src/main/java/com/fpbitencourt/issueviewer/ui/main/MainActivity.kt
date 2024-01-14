package com.fpbitencourt.issueviewer.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fpbitencourt.issueviewer.R
import com.fpbitencourt.issueviewer.data.model.Issue
import com.fpbitencourt.issueviewer.databinding.ActivityMainBinding
import com.fpbitencourt.issueviewer.ui.issue.IssueDetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: IssueListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[IssueListViewModel::class.java]

        setContentView(binding.root)

        setupToolbar()
        setupViewModel()
        setupSearchButton()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        title = getString(R.string.main_activity_issues)
    }

    private fun setupViewModel() {
        viewModel.issues.observe(this) { issues ->
            (binding.recyclerView.adapter as? IssueListAdapter)?.setIssues(issues)
        }

        viewModel.errorEvent.observe(this) {
            showMessage(getString(R.string.error_message))
        }

        viewModel.loadingIssues.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.defaultRepositoryUrl.observe(this) { defaultValue ->
            binding.editTextSearch.setText(defaultValue)
        }
    }

    private fun setupSearchButton() {
        binding.buttonSearch.setOnClickListener {
            val searchTerm = binding.editTextSearch.text.toString().trim()
            if (searchTerm.isNotEmpty()) {
                viewModel.loadIssues(searchTerm)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = IssueListAdapter().apply {
                setOnIssueClickListener(object : IssueListAdapter.OnIssueClickListener {
                    override fun onIssueClick(issue: Issue) {
                        openIssueDetailActivity(issue)
                    }
                })
            }
        }
    }

    private fun openIssueDetailActivity(issue: Issue) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra(IssueDetailActivity.EXTRA_ISSUE, issue)
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}