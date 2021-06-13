package nz.co.warehouseandroidtest

import nz.co.warehouseandroidtest.common.BRANCH_ID
import nz.co.warehouseandroidtest.common.MACHINE_ID
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.productdetail.data.models.Price
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetail
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse


class TestUtils {


    companion object Fake {
        val fakeUserResponse = UserResponse("Prod","5907F2A1-6D84-4A46-85CA-6BDF043B7813")

        fun mapSearchQuery(query: String): Map<String, Any> {
            return  mapOf("Search" to query, "MachineID" to MACHINE_ID, "UserID" to "5907F2A1-6D84-4A46-85CA-6BDF043B7813",
                "Branch" to BRANCH_ID, "Start" to 0, "Limit" to 20) }

        val fakeSearchResult = SearchResponse(
            "Y","233","prod", listOf(),"3397"
        )

        fun mapProductQuery(barcode: String): Map<String, Any> {
            return  mapOf("Barcode" to barcode, "MachineID" to MACHINE_ID, "UserID" to "5907F2A1-6D84-4A46-85CA-6BDF043B7813")
        }

        val fakeProductResult = ProductDetailsResponse(
            "Y","233","1234", "prod", ProductDetail(
                "11","11","11","11","11","11",
                "11","11","11","11","11","11",
                Price("11","11"),"11","11","11","11","11"

            ),"3397","2021-06-13T12:03:55","1867795","","5907F2A1-6D84-4A46-85CA-6BDF043B7813"
        )

    }



}