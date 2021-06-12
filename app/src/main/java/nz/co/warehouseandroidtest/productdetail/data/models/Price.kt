package nz.co.warehouseandroidtest.productdetail.data.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Price(
    @Expose @SerializedName("Price")
    val price: String,
    @Expose @SerializedName("Type")
    val type: String
) : Parcelable