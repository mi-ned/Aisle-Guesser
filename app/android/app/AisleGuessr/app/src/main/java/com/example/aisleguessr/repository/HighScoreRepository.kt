package com.example.aisleguessr.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.aisleguessr.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HighScoreRepository(private val context: Context){

    private companion object {
        val HIGH_SCORE_KEY = intPreferencesKey("high_score")
    }

    val highScoreFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[HIGH_SCORE_KEY] ?: 0
        }

    suspend fun saveHighScore(score: Int){
        context.dataStore.edit { preferences ->
            preferences[HIGH_SCORE_KEY] = score
        }
    }
}