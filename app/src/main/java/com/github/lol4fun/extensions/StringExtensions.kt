package com.github.lol4fun.extensions

import com.github.lol4fun.R
import com.github.lol4fun.core.model.Server
import com.github.lol4fun.util.ConstantsUtil
import android.text.Editable

fun String.setServerByCode(): Server? {
    return when (this) {
        ConstantsUtil.Match.BR_CODE -> Server(this, R.string.br_code, R.string.br_name)
        ConstantsUtil.Match.RU_CODE -> Server(this, R.string.ru_code, R.string.ru_name)
        ConstantsUtil.Match.KR_CODE -> Server(this, R.string.kr_code, R.string.kr_name)
        ConstantsUtil.Match.OCE_CODE -> Server(this, R.string.oce_code, R.string.oce_name)
        ConstantsUtil.Match.JP_CODE -> Server(this, R.string.jp_code, R.string.jp_name)
        ConstantsUtil.Match.NA_CODE -> Server(this, R.string.na_code, R.string.na_name)
        ConstantsUtil.Match.EUN_CODE -> Server(this, R.string.eun_code, R.string.eun_name)
        ConstantsUtil.Match.EUW_CODE -> Server(this, R.string.euw_code, R.string.euw_name)
        ConstantsUtil.Match.TR_CODE -> Server(this, R.string.tr_code, R.string.tr_name)
        ConstantsUtil.Match.LAN_CODE -> Server(this, R.string.lan_code, R.string.lan_name)
        ConstantsUtil.Match.LAS_CODE -> Server(this, R.string.las_code, R.string.las_name)
        else -> null
    }
}

fun String.getTextByEditable(): Editable {
    return Editable.Factory.getInstance().newEditable(this)
}