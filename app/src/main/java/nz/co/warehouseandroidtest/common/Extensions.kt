package nz.co.warehouseandroidtest.common

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pd.chocobar.ChocoBar

fun Activity.loge(message: String){
    Log.e(this::class.java.simpleName, message)
}

fun Activity.longtoast(message: String){
    Toast.makeText(this, message , Toast.LENGTH_LONG).show();
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.snackBarSuccess(message: String){
    ChocoBar.builder().setActivity(this)
        .setText(message)
        .setDuration(ChocoBar.LENGTH_SHORT)
        .green()
        .show();
}

fun Activity.snackBarInfo(message: String){
    ChocoBar.builder().setActivity(this)
        .setText(message)
        .setDuration(ChocoBar.LENGTH_SHORT)
        .orange()
        .show();
}

fun Activity.snackBarError(message: String){
    ChocoBar.builder().setActivity(this)
        .setText(message)
        .setDuration(ChocoBar.LENGTH_SHORT)
        .red()
        .show();
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}