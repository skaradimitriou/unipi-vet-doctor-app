package com.stathis.data.repositories

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.stathis.data.dto.AppointmentDto
import com.stathis.data.dto.DoctorInfoDto
import com.stathis.data.dto.UserInfoDto
import com.stathis.data.mappers.DoctorInfoMapper
import com.stathis.data.mappers.ProfileInfoMapper
import com.stathis.data.utils.compressBitmap
import com.stathis.data.utils.toListOf
import com.stathis.domain.model.Appointment
import com.stathis.domain.model.DoctorInfo
import com.stathis.domain.model.UserInfo
import com.stathis.domain.repositories.DoctorRepository
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: StorageReference
) : DoctorRepository {

    override suspend fun getDoctorData(): DoctorInfo {
        val result = firestore.collection("doctor")
            .get()
            .await()
            .toListOf<DoctorInfoDto>()
            .getOrNull(0)

        return DoctorInfoMapper.toDomainModel(result)
    }

    override suspend fun getDoctorAppointments(): List<Appointment> {
        val result = firestore.collection("appointments")
            .get()
            .await()
            .toListOf<AppointmentDto>()

        val myAppintments = result
            .filter { it.vet?.fullName == "Νικόλαος Γιαννάκης" }
            .map {
                val userInfo = getProfileInfo(it.uuid.toString())
                Appointment(
                    patientName = "${userInfo.firstName} ${userInfo.lastName}",
                    patientImage = userInfo.userImg,
                    dateAndTime = it.appointmentDateAndTime.toString()
                )
            }.filter {
                it.dateAndTime.isTodaysAppointment()
            }

        return myAppintments
    }

    override suspend fun fetchAppointmentsForDate(date: Date): List<Appointment> {
        val result = firestore.collection("appointments")
            .get()
            .await()
            .toListOf<AppointmentDto>()

        val myAppintments = result
            .filter { it.vet?.fullName == "Νικόλαος Γιαννάκης" }
            .map {
                val userInfo = getProfileInfo(it.uuid.toString())
                Appointment(
                    patientName = "${userInfo.firstName} ${userInfo.lastName}",
                    patientImage = userInfo.userImg,
                    dateAndTime = it.appointmentDateAndTime.toString()
                )
            }.filter {
                it.dateAndTime.isTodaysAppointment(date)
            }

        return myAppintments
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

    override suspend fun updateDoctorDetails(email: String, telephone: String): Boolean {
        val result = firestore.collection("doctor")
            .get()
            .await()
            .toListOf<DoctorInfoDto>()
            .getOrNull(0)

        val docDetails = DoctorInfoMapper.toDomainModel(result)
        val newDetails = docDetails.details.apply {
            this.contact.email = email
            this.contact.telephone = telephone
        }

        val data: HashMap<String, Any> = hashMapOf(
            "details" to newDetails
        )

        firestore.collection("doctor").document("KZNz18NTLMSK34ESmzUG").update(data).await()
        return true
    }

    override suspend fun getProfileInfo(uuid: String): UserInfo {
        val result = firestore.collection("users")
            .document(uuid)
            .get()
            .await()
            .toObject(UserInfoDto::class.java)

        return ProfileInfoMapper.toDomainModel(result)
    }

    fun String.isTodaysAppointment(date: Date? = null): Boolean {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val outputDate = input.parse(this)
        val sdf = SimpleDateFormat("dd/MM")
        val firstMonth = sdf.format(outputDate)
        val secondMonth = sdf.format(date ?: Date())
        return firstMonth == secondMonth
    }
}