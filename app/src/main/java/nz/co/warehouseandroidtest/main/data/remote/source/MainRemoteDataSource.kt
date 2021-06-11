package nz.co.warehouseandroidtest.main.data.remote.source

import nz.co.warehouseandroidtest.main.data.models.UserResponse


interface MainRemoteDataSource {

    suspend fun getUserId():  UserResponse

}