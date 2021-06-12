package nz.co.warehouseandroidtest.productdetail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.co.warehouseandroidtest.productdetail.presentation.viewmodel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.di.ViewModelKey

@Module
abstract class ProductPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindsHomeViewModel(mProductViewModel: ProductViewModel): ViewModel
}