package bit.hemant.git.trends.feature_repo.data.data_source.remote.network

data class Permissions(
    var admin: Boolean?,
    var maintain: Boolean?,
    var pull: Boolean?,
    var push: Boolean?,
    var triage: Boolean?
)