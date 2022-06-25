package bit.hemant.git.trends.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreRepoResponseTime(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        val PREF_REPO_RESPONSE_TIME = longPreferencesKey("pref_repo_response_time")
    }

    //get the saved email
    val getTime: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_REPO_RESPONSE_TIME] ?: 0L
        }


    suspend fun updateRepoResponseTime(epoch: Long) {
        context.dataStore.edit { settings ->
            settings[PREF_REPO_RESPONSE_TIME] = epoch
        }
    }


}