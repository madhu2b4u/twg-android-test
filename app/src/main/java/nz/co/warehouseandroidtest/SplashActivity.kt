package nz.co.warehouseandroidtest

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nz.co.warehouseandroidtest.main.presentation.ui.activity.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val activityScope = CoroutineScope(Dispatchers.Main)

        activityScope.launch {
            delay(2000)
            navigateToIntro()
        }

        val myanim: Animation = AnimationUtils.loadAnimation(this, R.anim.mysplashanimation )
        ivSplash.startAnimation(myanim)
    }

    private fun navigateToIntro(){
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}