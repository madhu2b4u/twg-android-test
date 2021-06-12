package nz.co.warehouseandroidtest.productdetail.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSource
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSourceImpl
import nz.co.warehouseandroidtest.productdetail.data.remote.services.ProductService
@Module(includes = [ProductRemoteModule.Binders::class])
class ProductRemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: ProductRemoteDataSourceImpl
        ): ProductRemoteDataSource
    }
    @Provides
    fun providesProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)


}