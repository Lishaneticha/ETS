package gov.et.ets.feature_users.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acs.ahadumonitor.feature_users.data.source.local.dao.UserDao
import gov.et.ets.feature_users.domain.model.User

@Database(
    entities = [User::class],
    version = 2,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}