package bit.hemant.git.trends.feature_repo.data.data_source.remote.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class License {
    var key: String? = null
    var name: String? = null
    var node_id: String? = null
    var spdx_id: String? = null
    var url: String? = null
}