package com.smile.boda.api.net

import com.smile.boda.App
import com.smile.boda.PrefUtil
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object NetModule {
    private fun provideOkhttpClient(): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(App.getsInstance().cacheDir, cacheSize.toLong())
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        client.addNetworkInterceptor(CacheInterceptor())
        client.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            PrefUtil.getToken()?.let {
                request.addHeader("Content-Type", "application/json")
                request.addHeader("Accept", "application/json")
                request.addHeader("Authorization", "Bearer $it")
            }
            chain.proceed(request.build())
        }

        client.connectTimeout(20, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
        client.cache(cache)
        return client.build()
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.BASE_URL)
                .client(provideOkhttpClient())
                .build()
    }

    private class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response? {
            return if (chain?.request()?.header("Cache-Control") != null) {
                Response.Builder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "max-age=2400")
                        .build()
            } else {
                chain?.proceed(chain.request())
            }
        }
    }

    private class NullOnEmptyConverterFactory : Converter.Factory() {

        override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, Any>? {
            val delegate = retrofit!!.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
            return Converter { body -> if (body.contentLength() == 0L) null else delegate.convert(body) }
        }
    }
}