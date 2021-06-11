package nz.co.warehouseandroidtest.search.di

import nz.co.warehouseandroidtest.search.data.repository.SearchRepository
import nz.co.warehouseandroidtest.search.data.repository.SearchRepositoryImpl
import nz.co.warehouseandroidtest.search.domain.SearchUseCaseImpl
import nz.co.warehouseandroidtest.search.domain.SearchUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class SearchDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: SearchRepositoryImpl
    ): SearchRepository


    @Binds
    abstract fun bindsArticlesUseCase(
        mSearchUseCase: SearchUseCaseImpl
    ): SearchUseCase


}