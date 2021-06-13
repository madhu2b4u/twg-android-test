package nz.co.warehouseandroidtest.main.presentation.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.SpUtil
import nz.co.warehouseandroidtest.common.USERID
import nz.co.warehouseandroidtest.permission.PermissionActivity
import nz.co.warehouseandroidtest.permission.PermissionChecker

class MainActivity : AppCompatActivity() {

    var checker: PermissionChecker = PermissionChecker(this)

    val permission = Manifest.permission.CAMERA

    val REQUEST_PERMISSION_CODE = 0


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

        if (checker.lacksPermission(permission)) {
            PermissionActivity.startActivityForResult(this, REQUEST_PERMISSION_CODE, permission)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK != resultCode) {
            return
        }
        if (requestCode == REQUEST_PERMISSION_CODE && resultCode == PermissionActivity.PERMISSIONS_DENIED) {
            finish()
        }
    }


}