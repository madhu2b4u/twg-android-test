package nz.co.warehouseandroidtest.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionChecker(context: Context) {

    private var mContext: Context = context

    fun lacksPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (lacksPermission(permission)) {
                return true
            }
        }
        return false
    }

    internal fun lacksPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            mContext,
            permission
        ) == PackageManager.PERMISSION_DENIED
    }
}