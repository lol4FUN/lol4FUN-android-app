package com.github.lol4fun.core.repository.nickname

import com.github.lol4fun.core.api.Resource
import com.github.lol4fun.core.model.SummonerInfo
import com.github.lol4fun.core.repository.BaseRepository
import com.github.lol4fun.util.ConstantsUtil
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_REGION
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_REGION_BR
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_RIOT_ACCOUNT_ID
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_RIOT_ID
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_RIOT_PROFILE_ICON_ID
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS
import com.github.lol4fun.util.ErrorUtils
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestoreException
import java.util.*
import java.util.concurrent.ExecutionException

class NicknameRepository : BaseRepository() {

    suspend fun getSummonerNameByNameAtRiotApi(summonerName: String): Resource {
        return safeApiCall(
            call = { api.getSummonerNameByNameAsync(summonerName).await() }
        )
    }

    fun saveSummonerInfoAtFirestore(summonerInfo: SummonerInfo): Resource {
        val taskSaveSummonerInfo = db.collection(DATABASE_CUSTOMERS)
            .document(auth.uid ?: UUID.randomUUID().toString())
            .update(
                mapOf(
                    FIELD_USER_SUMMONER_NAME to summonerInfo.name,
                    FIELD_USER_REGION to FIELD_USER_REGION_BR,
                    FIELD_USER_RIOT_ACCOUNT_ID to summonerInfo.accountId,
                    FIELD_USER_RIOT_ID to summonerInfo.id,
                    FIELD_USER_RIOT_PROFILE_ICON_ID to summonerInfo.profileIconId
                )
            )
        return try {
            Tasks.await(taskSaveSummonerInfo)

            Resource.success(null)
        } catch (executionException: ExecutionException) {
            val error = ErrorUtils.parseError(executionException.cause as FirebaseFirestoreException)

            error?.message?.let {  message ->
                Resource.error(message)
            } ?: run {
                Resource.error(ConstantsUtil.Error.ERROR_DEFAULT)
            }
        } catch (interruptedException: InterruptedException) {
            Resource.error(ConstantsUtil.Error.ERROR_DEFAULT)
        }
    }
}