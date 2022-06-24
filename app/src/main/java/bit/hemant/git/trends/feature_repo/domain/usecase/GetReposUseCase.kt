package bit.hemant.git.trends.feature_repo.domain.usecase

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder
import bit.hemant.git.trends.feature_repo.domain.util.toRepoList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetReposUseCase(
    private val remote: GitRemoteRepository,
    private val local: GitLocalRepository
) {

    suspend operator fun invoke(repoOrder: RepoOrder = RepoOrder.Title): Flow<List<Repo>> {
        return flow {
            val remoteList = remote.getRepoList().toRepoList()
            local.insertRepos(remoteList)
            local.getRepoList().map { repo ->

                when (repoOrder) {
                    RepoOrder.Title -> repo.sortedBy { it.name }
                    RepoOrder.Star -> repo.sortedBy { it.starCount }
                }
            }
        }
    }
}