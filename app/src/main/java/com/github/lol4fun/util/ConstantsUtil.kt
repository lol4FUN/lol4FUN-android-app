package com.github.lol4fun.util

class ConstantsUtil {

    object Main {
        const val RC_SIGN_IN = 123
    }

    object FirestoreDataBaseNames{
        const val DATABASE_CUSTOMERS = "customers"
    }

    object FirestoreDataBaseFields{
        const val FIELD_USER_NAME = "name"
        const val FIELD_USER_EMAIL = "email"
        const val FIELD_USER_PREMIUM = "premiumUser"
        const val FIELD_USER_COLOR_PREFERENCE = "colorPreference"
        const val FIELD_USER_COLOR_PREFERENCE_DARK = 1
        const val FIELD_USER_COLOR_PREFERENCE_LIGHT = 2
        const val FIELD_USER_SUMMONER_NAME = "summonerName"
        const val FIELD_USER_REGION = "region"
        const val FIELD_USER_REGION_BR = "br1"
        const val FIELD_USER_RIOT_ACCOUNT_ID = "accountId"
        const val FIELD_USER_RIOT_ID = "id"
        const val FIELD_USER_RIOT_PROFILE_ICON_ID = "profileIconId"
    }

    object Api {
        //todo modularizar com link para regiao do usuário, hoje está apenas para o BR
        const val BASE_URL = "https://br1.api.riotgames.com/lol/"
        const val BASE_URL_DDRAGON = "http://ddragon.leagueoflegends.com/cdn/"
        const val BASE_URL_SQUARE_ASSET = "http://ddragon.leagueoflegends.com/cdn/9.24.2/img/champion/"
        const val HEADER_RIOT_TOKEN_NAME = "X-Riot-Token"
        const val HEADER_RIOT_TOKEN_VALUE = "RGAPI-2789729e-583b-4de5-a1cb-e71739ef360a"
    }

    object Error {
        const val ERROR_DEFAULT = "Erro desconhecido, por favor, tente novamente"
        const val ERROR_BAD_REQUEST = "Erro na chamada, por favor, contate o administrador"
        const val ERROR_UNAUTHORIZED = "Chamada não autorizada, por favor, contate o administrador"
    }

    object Champions {
        const val NUMBER_OF_COLUMNS_GRID_CHAMPIONS = 4
    }

    object Match {
        const val BLUE_SIDE = "Blue"
        const val RED_SIDE = "Red"
        const val FLAG_WIN = "Win"
        const val FLAG_LOSE = "Fail"
        const val BR_CODE = "BR1"
        const val RU_CODE = "RU"
        const val KR_CODE = "KR"
        const val OCE_CODE = "OC1"
        const val JP_CODE = "JP1"
        const val NA_CODE = "NA1"
        const val EUN_CODE = "EUN1"
        const val EUW_CODE = "EUW1"
        const val TR_CODE = "TR1"
        const val LAN_CODE = "LA1" //Confirmar dps
        const val LAS_CODE = "LA2" //Confirmar dps
    }

    object Home {
        const val PAGE_SIZE = 10
    }
}