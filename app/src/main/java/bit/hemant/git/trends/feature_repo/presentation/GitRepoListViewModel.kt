package bit.hemant.git.trends.feature_repo.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.usecase.RepoUseCase
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GitRepoListViewModel @Inject constructor(private val repoUseCase: RepoUseCase) : ViewModel() {

    private val _state = mutableStateOf(RepoState())
    val state: State<RepoState> = _state


    init {
        getRepos(RepoOrder.Title)
    }

    fun onEvent(event: RepoEvent) {
        when (event) {
            is RepoEvent.Order -> {
                if (state.value.repoOrder==event.repoOrder) {
                    return
                }
                getLocalRepo(event.repoOrder)
            }

            RepoEvent.Refresh -> {
                getRepos(state.value.repoOrder)
            }
        }
    }

    private fun getLocalRepo(repoOrder: RepoOrder) {
        viewModelScope.launch {
            repoUseCase.localReposUseCase.invoke(repoOrder).collect {
                handleRepoResponse(repoOrder,it)
            }
        }

    }


    private fun getRepos(repoOrder: RepoOrder) {
        viewModelScope.launch {
            repoUseCase.remoteReposUseCase.invoke(repoOrder).catch {
                getLocalRepo(repoOrder)
            }.collect {
                handleRepoResponse(repoOrder,it)
            }
        }
    }

    private fun handleRepoResponse(repoOrder: RepoOrder,result: AsyncResult<List<Repo>>) {
        when (result) {
            AsyncResult.Loading -> _state.value = state.value.copy(loading = true)
            is AsyncResult.ErrorMessage -> _state.value = state.value.copy(loading = false)
            is AsyncResult.Success -> {
                Log.e("SUCCESS","REPO CHANGES: ${result.data.joinToString { "${it.name} :: ${it.starCount}" }}")
                _state.value =
                    state.value.copy(loading = false, repos = result.data, repoOrder = repoOrder)
            }
            else -> {}
        }
    }
}