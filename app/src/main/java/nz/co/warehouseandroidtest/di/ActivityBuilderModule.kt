package nz.co.warehouseandroidtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.warehouseandroidtest.SplashActivity
import nz.co.warehouseandroidtest.productdetail.presentation.ui.activity.ProductDetailActivity


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributesProductDetailActivity(): ProductDetailActivity

}