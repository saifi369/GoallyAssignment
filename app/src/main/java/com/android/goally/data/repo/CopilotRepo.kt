package com.android.goally.data.repo

import com.android.goally.constants.AppConstant.Companion.DATA_FETCHING_ERROR
import com.android.goally.constants.WebServiceConstant
import com.android.goally.data.model.api.response.copilot.CopilotSuccessResponse
import com.android.goally.data.network.rest.api.CopilotApi
import com.android.goally.data.util.ResultHandler
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class CopilotRepo @Inject constructor(
    private val copilotApi: CopilotApi,
) {
    suspend fun getCopilots(): ResultHandler<CopilotSuccessResponse> {
        return when (val response = copilotApi.getCoPilots(WebServiceConstant.AUTHORIZATION)) {
            is NetworkResponse.Success -> {
                ResultHandler.Success(response.body)
            }

            is NetworkResponse.ServerError -> return ResultHandler.Error(
                response.body?.message ?: DATA_FETCHING_ERROR
            )

            is NetworkResponse.NetworkError -> return ResultHandler.Error(
                response.error.message ?: DATA_FETCHING_ERROR
            )

            is NetworkResponse.UnknownError -> return ResultHandler.Error(
                response.error.message ?: DATA_FETCHING_ERROR
            )
        }
    }
}