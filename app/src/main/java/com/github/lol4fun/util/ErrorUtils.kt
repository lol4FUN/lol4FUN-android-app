package com.github.lol4fun.util

import com.github.lol4fun.core.model.ApiError
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_BAD_REQUEST
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_DEFAULT
import com.github.lol4fun.util.ConstantsUtil.Error.ERROR_UNAUTHORIZED
import com.google.firebase.firestore.FirebaseFirestoreException
import retrofit2.Response

object ErrorUtils {

    fun parseError(response: Response<*>): ApiError? {
        val error = ApiError()

        val rawResponse = response.raw()

        error.statusCode = rawResponse.code()
        error.message = getFriendlyMessage(rawResponse.code())

        return error
    }

    fun parseError(firestoreException: FirebaseFirestoreException?): ApiError? {
        val error = ApiError()

        error.statusCode = firestoreException?.code?.value()
        error.message = getFriendlyMessage(error.statusCode)

        return error
    }

    private fun getFriendlyMessage(code: Int?): String? {
        return when(code) {
            400	-> ERROR_BAD_REQUEST
            401 -> ERROR_UNAUTHORIZED
            403	-> "Forbidden"
            404	-> "Data not found"
            405	-> "Method not allowed"
            415	-> "Unsupported media type"
            429	-> "Rate limit exceeded"
            500	-> "Internal server error"
            502	-> "Bad gateway"
            503	-> "Service unavailable"
            504 -> "Gateway timeout"
            FirebaseFirestoreException.Code.UNAVAILABLE.value() -> "O serviço está temporariamente indisponível, por favor, tente novamente"
            FirebaseFirestoreException.Code.UNKNOWN.value() -> ERROR_DEFAULT
            FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.value() -> "Cota do Firebase atingida, por favor, contate o administrador"
            FirebaseFirestoreException.Code.INTERNAL.value() -> "Erro interno no sistema, por favor, contate o administrador"
            FirebaseFirestoreException.Code.ABORTED.value() -> "Transação abortada devido a concorrência, por favor, tente novamente"
            FirebaseFirestoreException.Code.CANCELLED.value() -> "Operação cancelada pelo usuário"
            else -> ERROR_DEFAULT
        }
    }
}