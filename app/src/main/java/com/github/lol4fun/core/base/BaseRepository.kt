package com.github.lol4fun.core.base

import com.github.lol4fun.core.api.ApiService
import com.github.lol4fun.core.api.DDragonRiotApi
import com.github.lol4fun.core.api.Resource
import com.github.lol4fun.core.api.RiotApi
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_DEFAULT
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS
import com.github.lol4fun.util.ErrorUtils
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Response
import java.lang.Exception

open class BaseRepository {

    val api : RiotApi = ApiService.riotApi
    val apiDDragon: DDragonRiotApi = ApiService.dDragonRiotApi
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource {
        return safeApiResult(call)
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : Resource{
        try {
            val response = call.invoke()

            return if(response.isSuccessful) {
                Resource.success(response.body())
            } else {
                val error = ErrorUtils.parseError(response)

                error?.message?.let {  message ->
                    Resource.error(message)
                } ?: run {
                    Resource.error(ERROR_DEFAULT)
                }
            }
        } catch (error : Exception) {
            return Resource.error(ERROR_DEFAULT)
        }
    }

    fun getUserFirestore(): Customer? {
        val docRef = db
            .collection(DATABASE_CUSTOMERS)
            .document(FirebaseAuth.getInstance().currentUser?.uid ?: "")

        val result = Tasks.await(docRef.get())

        return result.toObject(Customer::class.java)
    }

    suspend fun getChampions(): Resource {
        return safeApiCall(
            call = { apiDDragon.getChampionsAsync() }
        )
    }

    suspend fun getItems(): Resource {
        return safeApiCall(
            call = { apiDDragon.getItemsAsync() }
        )
    }
}