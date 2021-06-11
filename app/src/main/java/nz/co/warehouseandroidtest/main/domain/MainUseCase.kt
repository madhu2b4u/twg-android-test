package nz.co.warehouseandroidtest.main.domain

import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.main.data.models.UserResponse

interface MainUseCase {

    suspend fun getUserId(): LiveData<Result<UserResponse>>


}