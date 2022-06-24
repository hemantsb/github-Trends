package bit.hemant.git.trends.feature_repo.data.data_source.remote

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo

class GitRemoteDataSourceImpl(val gitRemoteService: GitRemoteService) : GitRemoteDataSource {

    override suspend fun getAllRepos(): List<GitRemoteRepo> {
        return gitRemoteService.getAllRepos()
    }

}