package gov.et.ets.feature_users.network.model

import com.google.gson.annotations.SerializedName

class HostNetworkEntity (
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("ip")
    var ip: String? = null,

    @SerializedName("status")
    var status: String? = null
    )


