package nz.co.warehouseandroidtest.barcode

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uuzuche.lib_zxing.activity.CaptureFragment
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.uuzuche.lib_zxing.activity.CodeUtils.AnalyzeCallback
import com.uuzuche.lib_zxing.activity.CodeUtils.isLightEnable
import kotlinx.android.synthetic.main.activity_scan_barcode.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.snackBarError
import nz.co.warehouseandroidtest.productdetail.presentation.ui.activity.ProductDetailActivity

class ScanBarcodeActivity : AppCompatActivity() {

    private var isOpen = false

    private val captureFragment = CaptureFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)
      //  PermissionUtils.requestCameraPermission(this)
        iv_flashlight.setImageDrawable(getDrawable(R.mipmap.ic_flash_off_black_24dp))
        iv_flashlight.setOnClickListener {
            toggleFlashlight()
        }

        CodeUtils.setFragmentArgs(captureFragment, R.layout.fragment_zxing_scan)

        captureFragment.analyzeCallback = analyzeCallback

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_zxing_container, captureFragment).commit()

    }

    private val analyzeCallback: AnalyzeCallback = object : AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
            val intent = Intent()
            intent.setClass(this@ScanBarcodeActivity, ProductDetailActivity::class.java)
            intent.putExtra("barcode", result)
            startActivity(intent)
            finish()
        }

        override fun onAnalyzeFailed() {
            snackBarError("Oops, bar code analysis failed!")
            finish()
        }
    }

    private fun toggleFlashlight() {
        isOpen = if (!isOpen) {
            isLightEnable(true)
            iv_flashlight.setImageDrawable(getDrawable(R.mipmap.ic_flash_on_black_24dp))
            true
        } else {
            isLightEnable(false)
            iv_flashlight.setImageDrawable(getDrawable(R.mipmap.ic_flash_off_black_24dp))
            false
        }
    }


}





