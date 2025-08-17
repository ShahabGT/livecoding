package ir.shahabazimi.data.di


import ir.shahabazimi.data.remote.AuthApiService
import ir.shahabazimi.data.repository.AuthRepositoryImpl
import ir.shahabazimi.domain.repository.AuthRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://shahabazimi.ir/livecoding/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(AuthApiService::class.java)
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}