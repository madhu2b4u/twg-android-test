package nz.co.warehouseandroidtest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nz.co.warehouseandroidtest.WarehouseTestApp
import nz.co.warehouseandroidtest.main.data.remote.source.MainRemoteDataSource
import nz.co.warehouseandroidtest.main.di.MainDomainModule
import nz.co.warehouseandroidtest.main.di.MainPresentationModule
import nz.co.warehouseandroidtest.main.di.MainRemoteModule
import nz.co.warehouseandroidtest.productdetail.di.ProductDomainModule
import nz.co.warehouseandroidtest.productdetail.di.ProductPresentationModule
import nz.co.warehouseandroidtest.productdetail.di.ProductRemoteModule
import nz.co.warehouseandroidtest.search.di.SearchDomainModule
import nz.co.warehouseandroidtest.search.di.SearchPresentationModule
import nz.co.warehouseandroidtest.search.di.SearchRemoteModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        MainRemoteModule::class,MainPresentationModule::class,MainDomainModule::class,
        SearchRemoteModule::class, SearchPresentationModule::class, SearchDomainModule::class,
        ProductRemoteModule::class, ProductPresentationModule::class, ProductDomainModule::class
    ]
)
interface AppComponent : AndroidInjector<WarehouseTestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: WarehouseTestApp)
}