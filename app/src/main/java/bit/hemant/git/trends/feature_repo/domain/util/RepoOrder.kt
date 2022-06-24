package bit.hemant.git.trends.feature_repo.domain.util

sealed class RepoOrder() {
    object Title : RepoOrder()
    object Star : RepoOrder()
}