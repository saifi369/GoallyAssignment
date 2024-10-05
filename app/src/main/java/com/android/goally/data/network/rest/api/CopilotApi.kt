package com.android.goally.data.network.rest.api

import com.android.goally.constants.WebServiceConstant
import com.android.goally.data.model.api.ErrorResponse
import com.android.goally.data.model.api.response.copilot.CopilotSuccessResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CopilotApi {
  @GET(WebServiceConstant.GET_COPILOTS)
  suspend fun getCoPilots(
    @Header("Authorization") authorization: String
  ): NetworkResponse<CopilotSuccessResponse, ErrorResponse>
}