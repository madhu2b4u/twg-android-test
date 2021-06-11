package nz.co.warehouseandroidtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.warehouseandroidtest.SplashActivity


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): SplashActivity

}