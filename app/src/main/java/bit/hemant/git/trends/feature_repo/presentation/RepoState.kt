package bit.hemant.git.trends.feature_repo.presentation

import bit.hemant.git.trends.feature_repo.domain.model.Repo
import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder

data class RepoState(
    val repos: List<Repo> = emptyList<Repo>(),
    val repoOrder: RepoOrder = RepoOrder.Title,
    val loading: Boolean=false,
    val pullToRefresh: Boolean=false
)