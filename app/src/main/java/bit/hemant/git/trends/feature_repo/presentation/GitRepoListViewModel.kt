package bit.hemant.git.trends.feature_repo.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.usecase.GetReposUseCase
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GitRepoListViewModel @Inject constructor(val useCase: GetReposUseCase) : ViewModel() {

    private val _state = mutableStateOf(RepoState())
    val state: State<RepoState> = _state


    init {
        getRepos(RepoOrder.Title)
    }


    private fun getRepos(repoOrder: RepoOrder) {
        viewModelScope.launch {
            useCase.invoke(repoOrder).collect {
                handleRepoResponse(it)
            }
        }
    }

    private fun handleRepoResponse(result: AsyncResult<List<Repo>>) {
        when (result) {
            AsyncResult.Loading -> _state.value = state.value.copy(loading = true)
            is AsyncResult.ErrorMessage -> _state.value = state.value.copy(loading = false)
            is AsyncResult.Success -> {
                _state.value =
                    state.value.copy(loading = false, repos = result.data)
            }
            else -> {}
        }
    }
}