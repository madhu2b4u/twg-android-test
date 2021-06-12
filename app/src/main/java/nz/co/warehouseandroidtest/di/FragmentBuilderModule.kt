package nz.co.warehouseandroidtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.warehouseandroidtest.main.presentation.ui.fragments.MainFragment
import nz.co.warehouseandroidtest.productdetail.presentation.ui.fragments.ProductFragment
import nz.co.warehouseandroidtest.search.presentation.ui.fragments.SearchFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributesProductFragment(): ProductFragment

}