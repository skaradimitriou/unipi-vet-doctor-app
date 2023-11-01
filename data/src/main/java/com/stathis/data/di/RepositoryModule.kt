package com.stathis.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.repositories.DoctorRepositoryImpl
import com.stathis.domain.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDoctorRepository(
        firestore: FirebaseFirestore
    ): DoctorRepository = DoctorRepositoryImpl(firestore)
}