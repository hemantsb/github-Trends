package bit.hemant.git.trends.feature_repo.presentation

import bit.hemant.git.trends.feature_repo.domain.util.RepoOrder

sealed class RepoEvent {
    data class Order(val repoOrder: RepoOrder) : RepoEvent()
    object Refresh : RepoEvent()
    object PullRefresh : RepoEvent()
}