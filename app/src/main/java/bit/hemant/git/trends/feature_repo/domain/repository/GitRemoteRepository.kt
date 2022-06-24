package bit.hemant.git.trends.feature_repo.domain.repository

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo

interface GitRemoteRepository {

    suspend fun getRepoList(): List<GitRemoteRepo>
}