package com.example.s8107356assessment2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashboardEntity(
    val artistName: String,
    val albumTitle: String,
    val releaseYear: Int,
    val genre: String,
    val trackCount: Int,
    val description: String,
    val popularTrack: String
) : Parcelable

