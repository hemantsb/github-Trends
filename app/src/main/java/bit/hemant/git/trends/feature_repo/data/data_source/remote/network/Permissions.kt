package bit.hemant.git.trends.feature_repo.data.data_source.remote.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
class Permissions {
    var admin: Boolean? = null
    var maintain: Boolean? = null
    var pull: Boolean? = null
    var push: Boolean? = null
    var triage: Boolean? = null
}