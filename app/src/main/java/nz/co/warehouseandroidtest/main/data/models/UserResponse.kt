package nz.co.warehouseandroidtest.main.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @Expose @SerializedName("ProdQAT")
    val prodQAT: String,
    @Expose @SerializedName("UserID")
    val userID: String
) : Parcelable