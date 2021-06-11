package nz.co.warehouseandroidtest.main.di

import nz.co.warehouseandroidtest.main.data.repository.MainRepository
import nz.co.warehouseandroidtest.main.data.repository.MainRepositoryImpl
import nz.co.warehouseandroidtest.main.domain.MainUseCaseImpl
import nz.co.warehouseandroidtest.main.domain.MainUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class MainDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: MainRepositoryImpl
    ): MainRepository


    @Binds
    abstract fun bindsArticlesUseCase(
        mMainUseCase: MainUseCaseImpl
    ): MainUseCase


}