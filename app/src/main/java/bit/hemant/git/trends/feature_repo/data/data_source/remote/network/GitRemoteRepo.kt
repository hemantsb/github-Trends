package bit.hemant.git.trends.feature_repo.data.data_source.remote.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class GitRemoteRepo() {
    var forks_count: Int? = null
    var id: Int? = null
    var language: String? = null
    var name: String? = null
    var owner: Owner? = null
    var permissions: Permissions? = null
    var `private`: Boolean? = null
    var stargazers_count: Int? = null

    constructor(
        id: Int,
        name: String,
        owner: Owner,
        starCount: Int,
        forkCount: Int,
        language: String,
    ) : this() {
        this.forks_count = forkCount
        this.id = id
        this.language = language
        this.name = name
        this.owner = owner
        this.stargazers_count = starCount
    }
}