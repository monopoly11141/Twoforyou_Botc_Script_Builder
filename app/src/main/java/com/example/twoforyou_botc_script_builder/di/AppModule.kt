package com.example.twoforyou_botc_script_builder.di

import android.content.Context
import androidx.room.Room
import com.example.twoforyou_botc_script_builder.data.db.local.ScriptDao
import com.example.twoforyou_botc_script_builder.data.db.local.ScriptDb
import com.example.twoforyou_botc_script_builder.data.db.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.script_display.ScriptDisplayRepositoryImpl
import com.example.twoforyou_botc_script_builder.data.script_list.ScriptListRepositoryImpl
import com.example.twoforyou_botc_script_builder.data.select_character.SelectCharacterRepositoryImpl
import com.example.twoforyou_botc_script_builder.domain.script_display.ScriptDisplayRepository
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import com.example.twoforyou_botc_script_builder.domain.select_character.SelectCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesScriptDao(scriptDb: ScriptDb): ScriptDao = scriptDb.scriptDao

    @Provides
    @Singleton
    fun providesScriptDb(@ApplicationContext context: Context): ScriptDb =
        Room.databaseBuilder(context, ScriptDb::class.java, "script_database")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun providesFirebaseCharacterDatabase(): FirebaseCharacterDatabase {
        return FirebaseCharacterDatabase()
    }

    @Provides
    @Singleton
    fun providesScriptListRepository(
        scriptDao: ScriptDao,
        firebaseCharacterDatabase: FirebaseCharacterDatabase
    ): ScriptListRepository {
        return ScriptListRepositoryImpl(
            scriptDao,
            firebaseCharacterDatabase
        )
    }

    @Provides
    @Singleton
    fun providesScriptDisplayRepository(
        scriptDao: ScriptDao
    ): ScriptDisplayRepository {
        return ScriptDisplayRepositoryImpl(
            scriptDao
        )
    }

    @Provides
    @Singleton
    fun providesSelectCharacterRepository(): SelectCharacterRepository {
        return SelectCharacterRepositoryImpl(providesFirebaseCharacterDatabase())
    }


}