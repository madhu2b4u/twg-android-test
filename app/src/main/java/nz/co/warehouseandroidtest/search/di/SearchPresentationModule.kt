package nz.co.warehouseandroidtest.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.co.warehouseandroidtest.search.presentation.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.di.ViewModelKey

@Module
abstract class SearchPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsHomeViewModel(mSearchViewModel: SearchViewModel): ViewModel
}