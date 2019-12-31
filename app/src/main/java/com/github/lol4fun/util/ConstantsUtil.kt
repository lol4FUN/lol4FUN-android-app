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
        const val HEADER_RIOT_TOKEN_VALUE = "RGAPI-529915a1-9d95-4145-86ad-85ba26d90968"
    }

    object Error {
        const val ERROR_DEFAULT = "Erro desconhecido, por favor, tente novamente"
        const val ERROR_BAD_REQUEST = "Erro na chamada, por favor, contate o administrador"
        const val ERROR_UNAUTHORIZED = "Chamada não autorizada, por favor, contate o administrador"
    }

    object Champions {
        const val NUMBER_OF_COLUMNS_GRID_CHAMPIONS = 4
    }
}