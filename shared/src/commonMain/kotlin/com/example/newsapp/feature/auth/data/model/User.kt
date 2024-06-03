package com.example.newsapp.feature.auth.data.model

data class User(
    val username: String,
    val email: String,
    val password: String,
    val avatarUrl: String = BASE_IMAGE_URL,
) {
    companion object {
        const val BASE_IMAGE_URL =
            "https://sun9-16.userapi.com/impg/b-Ro5hyOxzkGoXhybZMRJ15LzJLQa8TB458hmw/CSp4khexwUU.jpg?size=1080x752&quality=96&sign=a9fd442538ebdfdf5d87d3fe05e1cc31&c_uniq_tag=2HpcQzaDcC6_j_fmwk1ongoJBOy0wRLxwoS9PonNBvU&type=album"
    }
}