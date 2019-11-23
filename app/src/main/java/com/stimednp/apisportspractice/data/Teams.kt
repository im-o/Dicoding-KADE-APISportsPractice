package com.stimednp.apisportspractice.data

import com.google.gson.annotations.SerializedName

/**
 * Created by rivaldy on 11/7/2019.
 */

data class Teams(
    @SerializedName("idTeam")
    var teamId: String? = null,
    @SerializedName("strTeam")
    var teamName: String? = null,
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)