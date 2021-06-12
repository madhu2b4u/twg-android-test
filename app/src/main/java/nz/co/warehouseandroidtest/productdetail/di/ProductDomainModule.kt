package nz.co.warehouseandroidtest.productdetail.di

import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepository
import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepositoryImpl
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCaseImpl
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class ProductDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: ProductRepositoryImpl
    ): ProductRepository


    @Binds
    abstract fun bindsArticlesUseCase(
        mProductUseCase: ProductUseCaseImpl
    ): ProductUseCase


}