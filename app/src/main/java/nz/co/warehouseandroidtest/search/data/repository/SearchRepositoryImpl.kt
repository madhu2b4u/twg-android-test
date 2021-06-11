package nz.co.warehouseandroidtest.search.data.repository


import androidx.lifecycle.liveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSource
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override suspend fun getSearchResults(map: Map<String, Any>)= liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.getSearchResults(map)
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


}