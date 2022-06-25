package bit.hemant.git.trends.feature_repo.data.repository

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.repository.GitLocalRepository

class FakeRepoLocalRepository : GitLocalRepository {

    private var repos = mutableListOf<Repo>()

    override suspend fun getRepoList(): List<Repo> {
        return repos
    }

    override suspend fun insertRepos(repos: List<Repo>) {
        this.repos.addAll(repos)
    }

}