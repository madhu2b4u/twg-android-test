package nz.co.warehouseandroidtest.main.data.repository


import androidx.lifecycle.liveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.main.data.remote.source.MainRemoteDataSource
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: MainRemoteDataSource
) : MainRepository {

    override suspend fun getUserId()= liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.getUserId()
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }
}