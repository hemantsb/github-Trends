package bit.hemant.git.trends.feature_repo.data.data_source.remote.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
class Owner {
    var avatar_url: String? = null
    var gravatar_id: String? = null
    var id: Int? = null
    var login: String? = null
}