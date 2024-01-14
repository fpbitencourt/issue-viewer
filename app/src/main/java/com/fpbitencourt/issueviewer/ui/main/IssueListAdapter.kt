package com.fpbitencourt.issueviewer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fpbitencourt.issueviewer.R
import com.fpbitencourt.issueviewer.data.model.Issue
import com.fpbitencourt.issueviewer.databinding.ItemIssueBinding

class IssueListAdapter : RecyclerView.Adapter<IssueListAdapter.ViewHolder>() {

    private var issues: List<Issue> = emptyList()
    private lateinit var onIssueClickListener: OnIssueClickListener

    interface OnIssueClickListener {
        fun onIssueClick(issue: Issue)
    }

    fun setIssues(newIssues: List<Issue>) {
        val diffResult = DiffUtil.calculateDiff(IssueDiffCallback(issues, newIssues))
        issues = newIssues
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnIssueClickListener(listener: OnIssueClickListener) {
        onIssueClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = issues[position]
        holder.bind(issue)
        holder.itemView.setOnClickListener {
            onIssueClickListener.onIssueClick(issue)
        }
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: Issue) {
            binding.titleTextView.text = issue.title

            when (issue.state.trim().lowercase()) {
                Issue.STATE_OPEN -> binding.stateImageView.setImageResource(R.drawable.ic_open_issue)
                Issue.STATE_CLOSED -> binding.stateImageView.setImageResource(R.drawable.ic_closed_issue)
            }

        }
    }

    class IssueDiffCallback(private val oldList: List<Issue>, private val newList: List<Issue>) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

}