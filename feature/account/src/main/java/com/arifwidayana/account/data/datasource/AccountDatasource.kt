package com.arifwidayana.account.data.datasource

import com.arifwidayana.account.data.service.AccountService

interface AccountDatasource {
}

class AccountDatasourceImpl(
    private val accountService: AccountService
): AccountDatasource {

}