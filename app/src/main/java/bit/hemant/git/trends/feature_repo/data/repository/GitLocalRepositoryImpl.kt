package bit.hemant.git.trends.feature_repo.data.repository

import bit.hemant.git.trends.feature_repo.data.data_source.local.RepoDao
import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder

class GitLocalRepositoryImpl(
    private val dao: RepoDao
) : GitLocalRepository {

    override suspend fun getRepoList(): List<Repo> {
        return dao.getAllRepo()
    }

    override suspend fun insertRepos(repos: List<Repo>) {
        dao.insertRepos(repos)
    }
}