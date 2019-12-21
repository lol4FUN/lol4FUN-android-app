package com.github.lol4fun.core.repository.nickname

import com.github.lol4fun.core.repository.BaseRepository
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_REGION
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_REGION_BR
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseNames.DATABASE_CUSTOMERS

class NicknameRepository : BaseRepository() {

    fun saveSummonerNameAndIds(summonerName: String) {
        db.collection(DATABASE_CUSTOMERS)
            .document(auth.uid ?: "")
            .update(
                mapOf(
                    FIELD_USER_SUMMONER_NAME to summonerName,
                    FIELD_USER_REGION to FIELD_USER_REGION_BR
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                } else {

                }
            }
    }


}