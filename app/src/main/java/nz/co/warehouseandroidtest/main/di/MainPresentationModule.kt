package nz.co.warehouseandroidtest.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.co.warehouseandroidtest.main.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.di.ViewModelKey

@Module
abstract class MainPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsHomeViewModel(mMainViewModel: MainViewModel): ViewModel
}