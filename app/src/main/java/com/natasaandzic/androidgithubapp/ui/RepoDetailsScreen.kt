package com.natasaandzic.androidgithubapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.natasaandzic.androidgithubapp.model.Repo
import com.natasaandzic.androidgithubapp.model.Tag
import com.natasaandzic.androidgithubapp.mvvm.GitHubViewModel
import com.natasaandzic.androidgithubapp.Constants
import com.natasaandzic.androidgithubapp.ui.theme.PurpleGrey80

@Composable
fun RepoDetailsScreen(repoName: String, viewModel: GitHubViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = repoName) {
        viewModel.getUserRepo(Constants.USERNAME, repoName)
        viewModel.getRepoTags(Constants.USERNAME, repoName)
    }

    val repoState by viewModel.repoState
    val tagsState by viewModel.tagState

    Box(modifier = Modifier
        .fillMaxSize()
        .background(PurpleGrey80)) {
        when {
            repoState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            repoState.error != null -> {
                Text(text = "${repoState.error}")
            }

            else -> {
                DetailsScreen(data = repoState.data!!, tags = tagsState.data!!)
            }
        }
    }

}


@Composable
fun DetailsScreen(data: Repo, tags: List<Tag>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        item {
            Header(
                data.user.username,
                data.name,
                data.user.imageUrl,
                data.forksCount,
                data.watchersCount,
            )
        }

        if (tags.isNotEmpty()) {
            items(tags) { (tagName, commit) ->
                TagItem(tagName, commit.sha)
            }
        } else {
            item {
                Text(text = "No tags found")
            }
        }
    }
}

@Composable
fun Header(
    fullName: String,
    userName: String,
    imageUrl: String,
    forksCount: Int,
    watchersCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(80.dp)
                .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = fullName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                StatItem(count = forksCount, label = "Forks")
                Spacer(modifier = Modifier.width(16.dp))
                StatItem(count = watchersCount, label = "Watchers")
            }
        }
    }
}

@Composable
fun StatItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp)
        )
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@Composable
fun TagItem(tagName: String, sha: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = tagName, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = sha, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.End)
        }
    }
}
