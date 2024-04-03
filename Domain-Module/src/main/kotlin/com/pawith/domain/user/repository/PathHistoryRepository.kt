package com.pawith.domain.user.repository

import com.pawith.userdomain.PathHistory

interface PathHistoryRepository {
    fun save(pathHistory: PathHistory) : PathHistory
}