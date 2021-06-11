package nz.co.warehouseandroidtest.search.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSource
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSourceImpl
import nz.co.warehouseandroidtest.search.data.remote.services.SearchService


@Module(includes = [SearchRemoteModule.Binders::class])
class SearchRemoteModule {


    @Module
    interface Binders {


        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: SearchRemoteDataSourceImpl
        ): SearchRemoteDataSource


    }


    @Provides
    fun providesSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)


}