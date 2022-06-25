package bit.hemant.git.trends.feature_repo.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import bit.hemant.git.trends.feature_repo.presentation.components.LoadingShimmerList
import bit.hemant.git.trends.feature_repo.presentation.components.NoRepoDataView
import bit.hemant.git.trends.feature_repo.presentation.components.RECIPE_IMAGE_HEIGHT
import bit.hemant.git.trends.feature_repo.presentation.components.RepoItem

@Preview
@Composable
fun RepoScreenPrview() {
    GitReposScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitReposScreen(viewModel: GitRepoListViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    var showMenu by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Trending") },
            actions = {
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Text(text = "Sort by stars")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Text(text = "Sort by title")
                    }
                }
            })
    }) {
        if (state.loading && state.repos.isEmpty()) {
            LoadingShimmerList(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (state.repos.isEmpty()) {
            NoRepoDataView() {
                viewModel.refresh()
            }
        } else {
            val collapsedState = remember() { mutableStateOf(-1) }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.repos) { repo ->
                    RepoItem(repo, collapsedState) {
                        collapsedState.value = if (collapsedState.value == it) -1 else it
                    }
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }
    }
}
