package bit.hemant.git.trends.feature_repo.domain.usecase

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocalReposUseCase(
    private val local: GitLocalRepository
) {

    suspend operator fun invoke(repoOrder: RepoOrder = RepoOrder.Title): Flow<AsyncResult<List<Repo>>> {
        return flow {
            emit(AsyncResult.Loading)
            val allRepos=local.getRepoList()
            val orderedRepo = when (repoOrder) {
                RepoOrder.Title -> allRepos.sortedBy { it.name }
                RepoOrder.Star -> allRepos.sortedBy { it.starCount }
            }
            emit(AsyncResult.Success(orderedRepo))
        }
    }
}