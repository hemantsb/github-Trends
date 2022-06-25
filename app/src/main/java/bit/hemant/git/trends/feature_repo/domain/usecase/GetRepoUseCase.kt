package bit.hemant.git.trends.feature_repo.domain.usecase

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import bit.hemant.git.trends.feature_repo.domain.util.AsyncResult
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import bit.hemant.git.trends.feature_repo.domain.util.toRepoList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRepoUseCase(
    private val remote: GitRemoteRepository,
    private val local: GitLocalRepository
) {

    suspend operator fun invoke(repoOrder: RepoOrder = RepoOrder.Title): Flow<AsyncResult<List<Repo>>> {
        return flow {
            emit(AsyncResult.Loading)
            val remoteList = remote.getRepoList().toRepoList()
            local.insertRepos(remoteList)
            val allRepos = local.getRepoList()
            val orderedRepo = when (repoOrder) {
                RepoOrder.Title -> allRepos.sortedBy { it.name }
                RepoOrder.Star -> allRepos.sortedBy { it.starCount }
            }
            emit(AsyncResult.Success(orderedRepo))
        }
    }
}