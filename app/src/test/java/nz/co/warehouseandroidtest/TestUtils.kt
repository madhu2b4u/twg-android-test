package nz.co.warehouseandroidtest

import nz.co.warehouseandroidtest.common.BRANCH_ID
import nz.co.warehouseandroidtest.common.MACHINE_ID
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse


 class TestUtils {


    companion object Fake {
        val fakeUserResponse = UserResponse("Prod","5907F2A1-6D84-4A46-85CA-6BDF043B7813")

        fun mapQuery(query: String): Map<String, Any> {
            return  mapOf("Search" to query, "MachineID" to MACHINE_ID, "UserID" to "5907F2A1-6D84-4A46-85CA-6BDF043B7813",
                "Branch" to BRANCH_ID, "Start" to 0, "Limit" to 20) }

        val fakeSearchResult = SearchResponse(
            "Y","233","prod", listOf(),"3397"
        )

    }



}