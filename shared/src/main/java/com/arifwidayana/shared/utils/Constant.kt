package com.arifwidayana.shared.utils

object Constant {
    // DATASTORE
    const val DB_NAME = "preloved.db"
    const val USER_PREFERENCE = "USER_PREFERENCE"
    const val PREF_USER_TOKEN = "PREF_USER_TOKEN"

    // PATH
    const val ID_PATH = "id"
    
    // STATUS
    const val AVAILABLE = "available"
    const val SOLD = "sold"
    const val PENDING = "pending"
    const val DECLINED = "declined"

    // QUERY
    const val PAGE = "page"
    const val PER_PAGE = "per_page"
    const val CATEGORY_ID = "category_id"
    const val SEARCH = "search"

    // INDEX
    const val DEFAULT_INDEX_PAGE = 1
    const val DEFAULT_INDEX_PER_PAGE = 10
    const val NETWORK_PAGE_SIZE = 25

    // LOGIN
    const val LOGIN_EMAIL_FIELD = 0
    const val LOGIN_PASSWORD_FIELD = 1

    // REGISTER
    const val REGISTER_FULLNAME_FIELD = 0
    const val REGISTER_EMAIL_FIELD = 1
    const val REGISTER_PASSWORD_FIELD = 2
    const val REGISTER_CONFIRM_PASSWORD_FIELD = 3
    const val REGISTER_PHONE_FIELD = 4
    const val REGISTER_ADDRESS_FIELD = 5
    const val REGISTER_CITY_FIELD = 6

    // PROFILE
    const val PROFILE_FULLNAME_FIELD = 0
    const val PROFILE_PHONE_NUMBER_FIELD = 1
    const val PROFILE_ADDRESS_FIELD = 2
    const val PROFILE_CITY_FIELD = 3
}