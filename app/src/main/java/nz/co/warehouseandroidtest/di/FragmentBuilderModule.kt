package nz.co.warehouseandroidtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.warehouseandroidtest.main.presentation.ui.fragments.MainFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment
}