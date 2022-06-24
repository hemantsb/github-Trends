package bit.hemant.git.trends.feature_repo.domain.util

import bit.hemant.git.trends.feature_repo.data.data_source.remote.network.GitRemoteRepo
import bit.hemant.git.trends.feature_repo.domain.model.Repo

fun GitRemoteRepo.toRepo(): Repo {
    return Repo(
        id = id,
        name = name.orDash(),
        owner = owner?.login.orDash(),
        starCount = stargazers_count ?: 0,
        forkCount = forks_count ?: 0,
        language = language.orDash()
    )
}

fun List<GitRemoteRepo>.toRepoList(): List<Repo> {
    return map { it.toRepo() }
}

fun String?.orDash(): String {
    if (this == null || this.isNullOrEmpty()) {
        return "-"
    }
    return this
}