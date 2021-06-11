package nz.co.warehouseandroidtest.main.data.repository

import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.common.Result


interface MainRepository {

    suspend fun getUserId(): LiveData<Result<UserResponse>>

}