package bit.hemant.git.trends.feature_repo.data.data_source.remote

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo
import retrofit2.http.GET

interface GitRemoteService {

    @GET("orgs/octokit/repos")
    suspend fun getAllRepos(): List<GitRemoteRepo>
}