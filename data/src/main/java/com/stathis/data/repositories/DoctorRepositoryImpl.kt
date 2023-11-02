package com.stathis.data.repositories

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.stathis.data.dto.DoctorInfoDto
import com.stathis.data.mappers.DoctorInfoMapper
import com.stathis.data.utils.compressBitmap
import com.stathis.data.utils.toListOf
import com.stathis.domain.model.DoctorInfo
import com.stathis.domain.repositories.DoctorRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage : StorageReference
) : DoctorRepository {

    override suspend fun getDoctorData(): DoctorInfo {
        val result = firestore.collection("doctor")
            .get()
            .await()
            .toListOf<DoctorInfoDto>()
            .getOrNull(0)

        return DoctorInfoMapper.toDomainModel(result)
    }

    override suspend fun updateDoctorImage(bitmap: Bitmap): Boolean {
        val storageRef = storage.child("doctor/KZNz18NTLMSK34ESmzUG")
        val image = bitmap.compressBitmap()
        val upload = storageRef.putBytes(image).await()
        val downloadUrl = upload.metadata?.reference?.downloadUrl?.await().toString()

        val data: HashMap<String, Any> = hashMapOf(
            "imageUrl" to downloadUrl
        )

        firestore.collection("doctor").document("KZNz18NTLMSK34ESmzUG").update(data).await()
        return true
    }
}