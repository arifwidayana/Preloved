package com.arifwidayana.shared.utils

object Constant {
    // DATASTORE
    const val DB_NAME = "preloved.db"
    const val USER_PREFERENCE = "USER_PREFERENCE"
    const val PREF_USER_TOKEN = "PREF_USER_TOKEN"

    // PATH
    const val ID_PATH = "id"

    // TYPE
    const val BUYER = "buyer"
    const val SELLER = "seller"

    // STATUS PRODUCT
    const val AVAILABLE = "available"
    const val SOLD = "sold"
    const val PENDING = "pending"

    // STATUS NOTIFICATION
    const val BID = "bid"
    const val DECLINED = "declined"
    const val ACCEPTED = "accepted"
    const val CREATE = "create"

    // QUERY
    const val PAGE = "page"
    const val PER_PAGE = "per_page"
    const val CATEGORY_ID = "category_id"
    const val SEARCH = "search"

    // DATE PATTERN
    const val PATTERN_FORMAT_DEFAULT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val PATTERN_FORMAT_1 = "dd-MM-yyyy HH:mm:ss"
    const val PATTERN_FORMAT_2 = "dd MMM yyyy, HH:mm 'WIB'"

    // DEFAULT CURRENCY
    const val ZERO_VALUE = 0

    // INDEX
    const val DEFAULT_INDEX_PAGE = 1
    const val DEFAULT_INDEX_PER_PAGE = 10
    const val NETWORK_PAGE_SIZE = 25

    // AUTH - LOGIN
    const val LOGIN_EMAIL_FIELD = 0
    const val LOGIN_PASSWORD_FIELD = 1

    // AUTH - REGISTER
    const val REGISTER_FULLNAME_FIELD = 0
    const val REGISTER_EMAIL_FIELD = 1
    const val REGISTER_PASSWORD_FIELD = 2
    const val REGISTER_CONFIRM_PASSWORD_FIELD = 3
    const val REGISTER_PHONE_FIELD = 4
    const val REGISTER_ADDRESS_FIELD = 5
    const val REGISTER_CITY_FIELD = 6

    // ACCOUNT - PROFILE
    const val PROFILE_FULLNAME_FIELD = 0
    const val PROFILE_PHONE_NUMBER_FIELD = 1
    const val PROFILE_ADDRESS_FIELD = 2
    const val PROFILE_CITY_FIELD = 3

    // ACCOUNT - PASSWORD
    const val PASSWORD_CURRENT_FIELD = 0
    const val PASSWORD_NEW_FIELD = 1
    const val PASSWORD_CONFIRM_NEW_FIELD = 2

    // SELL
    const val SELL_NAME_FIELD = 0
    const val SELL_DESCRIPTION_FIELD = 1
    const val SELL_PRICE_FIELD = 2
    const val SELL_CATEGORY_FIELD = 3
    const val SELL_IMAGE_FIELD = 4

    // NOTIFICATION
    const val NOTIFICATION_MESSAGE = 0
    const val NOTIFICATION_STATUS = 1

    // DELAY
    const val DELAY_AUTO_SLIDE = 3000L
    const val LAST_AUTO_SLIDE = 0L
}