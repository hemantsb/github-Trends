package bit.hemant.git.trends.feature_repo.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import bit.hemant.git.trends.feature_repo.presentation.components.LoadingShimmerList
import bit.hemant.git.trends.feature_repo.presentation.components.NoRepoDataView
import bit.hemant.git.trends.feature_repo.presentation.components.RECIPE_IMAGE_HEIGHT
import bit.hemant.git.trends.feature_repo.presentation.components.RepoItem
import bit.hemant.git.trends.ui.theme.Typography
import bit.hemant.git.trends.ui.theme.lightBody
import bit.hemant.git.trends.ui.theme.theme40

@Preview
@Composable
fun RepoScreenPrview() {
    GitReposScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitReposScreen(viewModel: GitRepoListViewModel = hiltViewModel()) {


    val MENU_BUTTON_WIDTH = 120
    val state = viewModel.state.value
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val expanded = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Trending") },
            backgroundColor = theme40,
            actions = {
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "image",
                        tint = Color.Gray,
                    )
                }
                DropdownMenu(expanded = expanded.value,
                    offset = DpOffset((-80).dp, (-40).dp),
                    onDismissRequest = { expanded.value = false }) {

                    IconButton(modifier = Modifier
                        .width(MENU_BUTTON_WIDTH.dp)
                        .padding(end = 8.dp),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Sort by stars",
                            Modifier.padding(start = 12.dp),
                            style = Typography.headlineSmall,
                            color = lightBody
                        )
                    }

                    IconButton(modifier = Modifier
                        .width(MENU_BUTTON_WIDTH.dp)
                        .padding(end = 8.dp),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Sort by title",
                            Modifier.padding(start = 12.dp),
                            style = Typography.headlineSmall,
                            color = lightBody
                        )
                    }


                }
            }
        )
    }, scaffoldState = scaffoldState) {
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
