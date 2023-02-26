package com.arifwidayana.account.data.repository

import com.arifwidayana.account.data.datasource.AccountDatasource
import com.arifwidayana.shared.data.repository.Repository

interface AccountRepository {
}

class AccountRepositoryImpl(
    private val accountDatasource: AccountDatasource
): AccountRepository, Repository() {

}