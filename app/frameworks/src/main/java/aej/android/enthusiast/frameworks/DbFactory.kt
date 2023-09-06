package aej.android.enthusiast.frameworks

import aej.android.enthusiast.frameworks.database.CryptoFeedDao
import aej.android.enthusiast.frameworks.database.LocalCryptoFeedItem
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class DbFactory {
    @Database(
        entities = [LocalCryptoFeedItem::class],
        version = 1,
        exportSchema = false
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun cryptoFeedDao(): CryptoFeedDao
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun createDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database_app"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}