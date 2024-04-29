package com.example.twoforyou_botc_script_builder.di

import com.example.twoforyou_botc_script_builder.data.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.script_list.ScriptListRepositoryImpl
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.text.Typography.dagger

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

}