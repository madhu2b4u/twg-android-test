package nz.co.warehouseandroidtest.permission

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import nz.co.warehouseandroidtest.R

class PermissionActivity : AppCompatActivity() {

    private var mChecker: PermissionChecker? = null

    private var isRequireCheck: Boolean = false

    private val permissions: Array<String> get() = intent.getStringArrayExtra(EXTRA_PERMISSIONS)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (intent == null || !intent.hasExtra(EXTRA_PERMISSIONS)) {
            throw RuntimeException("PermissionsActivity needs to be started by static method startActivityForResult!")
        }
        setContentView(R.layout.activity_permisson)
        mChecker = PermissionChecker(this)
        isRequireCheck = true

    }

    override fun onResume() {
        super.onResume()
        if (isRequireCheck) {
            val permissions = permissions
            if (mChecker!!.lacksPermissions(*permissions)) {
                requestPermissions(*permissions)
            } else {
                allPermissionsGranted()
            }
        } else {
            isRequireCheck = true
        }
    }


    private fun requestPermissions(vararg permissions: String) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
    }

    private fun allPermissionsGranted() {
        setResult(PERMISSIONS_GRANTED)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true
            allPermissionsGranted()
        } else {
            isRequireCheck = false
            showMissingPermissionDialog()

        }
    }

    private fun hasAllPermissionsGranted(grantResults: IntArray): Boolean {

        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    private fun showMissingPermissionDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Help")
        builder.setMessage("Current app lacks necessary permissions. \n\nPlease click \"Settings\" - \"Permission\" - to grant permissions. \n\nThen click back button twice to return.")
        builder.setNeutralButton("Quit") { dialog, which ->
            setResult(PERMISSIONS_DENIED)
            finish()
        }

        builder.setPositiveButton("Settings") { dialog, which -> startAppSettings() }

        builder.show()
    }


    private fun startAppSettings() {

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse(PACKAGE_URL_SCHEME + packageName)
        startActivity(intent)

    }

    companion object {

        const val PERMISSIONS_GRANTED = 0
        const val PERMISSIONS_DENIED = 1
        private const val PERMISSION_REQUEST_CODE = 0
        private const val EXTRA_PERMISSIONS = "extra.permisson"
        private const val PACKAGE_URL_SCHEME = "package:"

        fun startActivityForResult(
            activity: Activity,
            requestCode: Int,
            vararg permissions: String) {

            val intent = Intent(activity, PermissionActivity::class.java)
            intent.putExtra(EXTRA_PERMISSIONS, permissions)
            ActivityCompat.startActivityForResult(activity, intent, requestCode, null)

        }

    }
}