package bit.hemant.git.trends.feature_repo.data.data_source.remote

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo

interface GitRemoteDataSource {
    suspend fun getAllRepos(): List<GitRemoteRepo>
}