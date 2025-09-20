package com.example.aisleguessr.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences

private const val HIGH_SCORE_NAME = "high_score"
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = HIGH_SCORE_NAME)
