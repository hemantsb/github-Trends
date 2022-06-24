package bit.hemant.git.trends.feature_repo.domain.repository

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GitLocalRepository {

    fun getRepoList(): Flow<List<Repo>>

    suspend fun insertRepos(repos: List<Repo>)
}