package bit.hemant.git.trends.feature_repo.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GitReposScreen(viewModel: GitRepoListViewModel = hiltViewModel()) {

    Text(text = "This is REpos")
}