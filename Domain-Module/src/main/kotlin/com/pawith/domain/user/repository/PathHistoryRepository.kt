package com.pawith.domain.user.repository

import com.pawith.domain.user.PathHistory

interface PathHistoryRepository {
    fun save(pathHistory: PathHistory) : PathHistory
}