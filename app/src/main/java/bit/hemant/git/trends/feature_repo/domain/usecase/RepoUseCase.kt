package bit.hemant.git.trends.feature_repo.domain.usecase

class RepoUseCase(
    val localReposUseCase: GetLocalReposUseCase,
    val remoteReposUseCase: GetRepoUseCase
)