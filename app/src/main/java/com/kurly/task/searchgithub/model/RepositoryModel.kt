package com.kurly.task.searchgithub.model

import com.google.gson.annotations.SerializedName

data class RepositoriesModel(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: ArrayList<RepositoryModel>
)

data class RepositoryModel(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("private") val private: Boolean,
    @SerializedName("owner") val owner: RepositoryOwnerModel
)

data class RepositoryOwnerModel(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val AvatarUrl: String
)