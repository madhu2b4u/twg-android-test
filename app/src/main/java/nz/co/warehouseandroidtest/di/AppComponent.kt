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
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class, MainRemoteModule::class,MainPresentationModule::class,MainDomainModule::class
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