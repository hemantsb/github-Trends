package bit.hemant.git.trends.feature_repo.data.repository

import bit.hemant.git.trends.feature_repo.data.data_source.remote.GitRemoteDataSource
import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo
import bit.hemant.git.trends.feature_repo.domain.repository.GitRemoteRepository
import kotlinx.coroutines.flow.flow

class GitRemoteRepositoryImpl(
    private val remoteDataSource: GitRemoteDataSource
) : GitRemoteRepository {

    override suspend fun getRepoList(): List<GitRemoteRepo> {
        return remoteDataSource.getAllRepos()
    }
}