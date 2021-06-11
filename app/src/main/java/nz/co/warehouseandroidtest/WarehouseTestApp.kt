package nz.co.warehouseandroidtest

import com.uuzuche.lib_zxing.activity.ZXingLibrary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import nz.co.warehouseandroidtest.common.SpUtil
import nz.co.warehouseandroidtest.di.DaggerAppComponent

class WarehouseTestApp : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        ZXingLibrary.initDisplayOpinion(this)
        SpUtil.instance?.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()

    }

}