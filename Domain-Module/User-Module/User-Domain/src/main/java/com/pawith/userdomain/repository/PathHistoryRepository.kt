package com.pawith.userdomain.repository

import com.pawith.userdomain.PathHistory

interface PathHistoryRepository {
    fun save(pathHistory: PathHistory) : PathHistory
}