package com.example.mybonaiapp.model

import com.google.gson.annotations.SerializedName

data class Content(
    val adFormat: String,
    @SerializedName("content")
    val content: Object,
    val contentFormatSource: String,
    val contentType: String,
    val externalTracking: ExternalTrackingX,
    val placement: String
)