package nz.co.warehouseandroidtest.main.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.SpUtil
import nz.co.warehouseandroidtest.common.USERID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPrefUtil = SpUtil.instance!!

        val userId = sharedPrefUtil.getString(USERID).toString()

        if (userId.isNotEmpty()){
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment).popBackStack()
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
                .navigate(R.id.searchFragment)
        }


    }


}