package com.example.peliculasejemplo.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class City(
        @PrimaryKey val codigo: Int,
        val titulo: String?
) : Parcelable