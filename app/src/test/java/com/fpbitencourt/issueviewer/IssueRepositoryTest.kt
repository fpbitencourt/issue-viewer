package com.fpbitencourt.issueviewer

import com.fpbitencourt.issueviewer.data.repository.IssueRepository
import io.mockk.every
import io.mockk.mockk
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IssueRepositoryTest {

    private lateinit var mockClient: OkHttpClient
    private lateinit var issueRepository: IssueRepository

    @Before
    fun setup() {
        mockClient = mockk()
        issueRepository = IssueRepository(mockClient)
    }

    @Test
    fun `getIssues success`() {
        val mockCall: Call = mockk()
        val mockResponse: Response = mockk()
        val responseBody = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"node_id\": \"MDU6SXNzdWUx\",\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347\",\n" +
                "    \"repository_url\": \"https://api.github.com/repos/octocat/Hello-World\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/labels{/name}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/comments\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/events\",\n" +
                "    \"html_url\": \"https://github.com/octocat/Hello-World/issues/1347\",\n" +
                "    \"number\": 1347,\n" +
                "    \"state\": \"open\",\n" +
                "    \"title\": \"Found a bug\",\n" +
                "    \"body\": \"I'm having a problem with this.\",\n" +
                "    \"user\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 1,\n" +
                "      \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"labels\": [\n" +
                "      {\n" +
                "        \"id\": 208045946,\n" +
                "        \"node_id\": \"MDU6TGFiZWwyMDgwNDU5NDY=\",\n" +
                "        \"url\": \"https://api.github.com/repos/octocat/Hello-World/labels/bug\",\n" +
                "        \"name\": \"bug\",\n" +
                "        \"description\": \"Something isn't working\",\n" +
                "        \"color\": \"f29513\",\n" +
                "        \"default\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"assignee\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 1,\n" +
                "      \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"assignees\": [\n" +
                "      {\n" +
                "        \"login\": \"octocat\",\n" +
                "        \"id\": 1,\n" +
                "        \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "        \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "        \"gravatar_id\": \"\",\n" +
                "        \"url\": \"https://api.github.com/users/octocat\",\n" +
                "        \"html_url\": \"https://github.com/octocat\",\n" +
                "        \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "        \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "        \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "        \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "        \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "        \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "        \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "        \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "        \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "        \"type\": \"User\",\n" +
                "        \"site_admin\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"milestone\": {\n" +
                "      \"url\": \"https://api.github.com/repos/octocat/Hello-World/milestones/1\",\n" +
                "      \"html_url\": \"https://github.com/octocat/Hello-World/milestones/v1.0\",\n" +
                "      \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/milestones/1/labels\",\n" +
                "      \"id\": 1002604,\n" +
                "      \"node_id\": \"MDk6TWlsZXN0b25lMTAwMjYwNA==\",\n" +
                "      \"number\": 1,\n" +
                "      \"state\": \"open\",\n" +
                "      \"title\": \"v1.0\",\n" +
                "      \"description\": \"Tracking milestone for version 1.0\",\n" +
                "      \"creator\": {\n" +
                "        \"login\": \"octocat\",\n" +
                "        \"id\": 1,\n" +
                "        \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "        \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "        \"gravatar_id\": \"\",\n" +
                "        \"url\": \"https://api.github.com/users/octocat\",\n" +
                "        \"html_url\": \"https://github.com/octocat\",\n" +
                "        \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "        \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "        \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "        \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "        \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "        \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "        \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "        \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "        \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "        \"type\": \"User\",\n" +
                "        \"site_admin\": false\n" +
                "      },\n" +
                "      \"open_issues\": 4,\n" +
                "      \"closed_issues\": 8,\n" +
                "      \"created_at\": \"2011-04-10T20:09:31Z\",\n" +
                "      \"updated_at\": \"2014-03-03T18:58:10Z\",\n" +
                "      \"closed_at\": \"2013-02-12T13:22:01Z\",\n" +
                "      \"due_on\": \"2012-10-09T23:39:01Z\"\n" +
                "    },\n" +
                "    \"locked\": true,\n" +
                "    \"active_lock_reason\": \"too heated\",\n" +
                "    \"comments\": 0,\n" +
                "    \"pull_request\": {\n" +
                "      \"url\": \"https://api.github.com/repos/octocat/Hello-World/pulls/1347\",\n" +
                "      \"html_url\": \"https://github.com/octocat/Hello-World/pull/1347\",\n" +
                "      \"diff_url\": \"https://github.com/octocat/Hello-World/pull/1347.diff\",\n" +
                "      \"patch_url\": \"https://github.com/octocat/Hello-World/pull/1347.patch\"\n" +
                "    },\n" +
                "    \"closed_at\": null,\n" +
                "    \"created_at\": \"2011-04-22T13:33:48Z\",\n" +
                "    \"updated_at\": \"2011-04-22T13:33:48Z\",\n" +
                "    \"closed_by\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 1,\n" +
                "      \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"author_association\": \"COLLABORATOR\",\n" +
                "    \"state_reason\": \"completed\"\n" +
                "  }\n" +
                "]"

        every { mockClient.newCall(any<Request>()) } returns mockCall
        every { mockCall.execute() } returns mockResponse
        every { mockResponse.body?.string() } returns responseBody

        val result = issueRepository.getIssues("https://test.com").test()

        result.assertComplete()
        result.assertValueCount(1)

        val issues = result.values()[0]
        assertEquals(1, issues.size)
        assertEquals("Found a bug", issues[0].title)
        assertEquals("I'm having a problem with this.", issues[0].body)
        assertEquals("https://api.github.com/repos/octocat/Hello-World/issues/1347", issues[0].url)
        assertEquals("2011-04-22T13:33:48Z", issues[0].createdAt)
        assertEquals("https://github.com/images/error/octocat_happy.gif", issues[0].user.avatarUrl)
    }

    @Test
    fun `getIssues error`() {
        val mockCall: Call = mockk()

        every { mockClient.newCall(any<Request>()) } returns mockCall
        every { mockCall.execute() } throws Exception("error")

        val result = issueRepository.getIssues("https://test.com").test()

        result.assertNotComplete()
        result.assertError { it.message == "error" }
    }
}
