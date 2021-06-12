package nz.co.warehouseandroidtest.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseFragment : DaggerFragment() {


    abstract fun layoutId(): Int

     var sharedPrefUtil = SpUtil.instance!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)
}