package com.example.twoforyou_botc_script_builder.di

import com.example.twoforyou_botc_script_builder.data.db.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.script_list.ScriptListRepositoryImpl
import com.example.twoforyou_botc_script_builder.data.select_character.SelectCharacterRepositoryImpl
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import com.example.twoforyou_botc_script_builder.domain.select_character.SelectCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesFirebaseCharacterDatabase(): FirebaseCharacterDatabase {
        return FirebaseCharacterDatabase()
    }

    @Provides
    @Singleton
    fun providesScriptListRepository(): ScriptListRepository {
        return ScriptListRepositoryImpl(providesFirebaseCharacterDatabase())
    }

    @Provides
    @Singleton
    fun providesSelectCharacterRepository() : SelectCharacterRepository {
        return SelectCharacterRepositoryImpl(providesFirebaseCharacterDatabase())
    }

}