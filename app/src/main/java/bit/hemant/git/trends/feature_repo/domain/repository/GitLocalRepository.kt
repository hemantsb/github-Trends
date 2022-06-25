package bit.hemant.git.trends.feature_repo.domain.repository

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder

interface GitLocalRepository {

    suspend fun getRepoList(): List<Repo>

    suspend fun insertRepos(repos: List<Repo>)
}