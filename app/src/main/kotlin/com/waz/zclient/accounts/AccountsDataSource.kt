package com.waz.zclient.accounts

import com.waz.zclient.accounts.domain.model.AccountMapper
import com.waz.zclient.accounts.domain.model.ActiveAccount
import com.waz.zclient.core.functional.map

class AccountsDataSource(
    private val accountMapper: AccountMapper,
    private val accountsLocalDataSource: AccountsLocalDataSource)
    : AccountsRepository {

    override suspend fun activeAccounts() = accountsLocalDataSource.activeAccounts()
        .map { entityList -> entityList.map { accountMapper.from(it) } }

    override suspend fun removeAccount(account: ActiveAccount) = accountsLocalDataSource.removeAccount(accountMapper.toEntity(account))

}
