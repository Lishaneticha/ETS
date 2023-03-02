package gov.et.ets.feature_users.network.response

import gov.et.ets.feature_users.network.model.HostNetworkEntity
import com.google.gson.annotations.SerializedName

class HostPingResult (
    @SerializedName("result")
    var result: List<HostNetworkEntity>
        )