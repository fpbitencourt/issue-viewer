package com.fpbitencourt.issueviewer.ui.issue

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fpbitencourt.issueviewer.data.model.Issue
import com.fpbitencourt.issueviewer.databinding.ActivityIssueDetailBinding
import com.squareup.picasso.Picasso
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class IssueDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ISSUE: String = "EXTRA_ISSUE"
    }

    private lateinit var binding: ActivityIssueDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedIssue: Issue? = getIssueParcelableExtra(intent)
        setupToolbar()
        updateUI(selectedIssue)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun updateUI(issue: Issue?) {
        if (issue != null) {
            binding.titleTextView.text = issue.title
            binding.descTextView.text = issue.body
            binding.createdAtTextView.text = formatZonedDateTime(issue.createdAt)
            binding.nameTextView.text = issue.user.login

            Picasso.get().load(issue.user.avatarUrl).into(binding.avatarImageView)

            binding.openBrowserButton.setOnClickListener {
                openBrowser(issue.url)
            }
        }
    }

    private fun openBrowser(url: String?) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            startActivity(this)
        }
    }

    private fun getIssueParcelableExtra(intent: Intent) : Issue? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(EXTRA_ISSUE, Issue::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_ISSUE)
        }
    }

    private fun formatZonedDateTime(dateTimeString: String): String {
        val zonedTime = ZonedDateTime.parse(dateTimeString)
        return zonedTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }

}
