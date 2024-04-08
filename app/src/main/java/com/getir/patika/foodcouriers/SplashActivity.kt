package com.getir.patika.foodcouriers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.getir.patika.foodcouriers.data.local.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_TIME)

            getUserId()

        }
    }

    private fun saveUserId(userId:String) {
        lifecycleScope.launch {
            try {
                dataStoreManager.saveUserId(userId)
            } catch (e: Exception) {
                // error
            }
        }
    }

    private fun getUserId() {
        lifecycleScope.launch {
            dataStoreManager.userId.collect { userId ->
                userId?.let {
                    startActivity(MainActivity.callIntent(this@SplashActivity))
                } ?: run {
                    startActivity(OnboardingActivity.callIntent(this@SplashActivity))
                }
            }
        }
    }


    companion object {
        private const val SPLASH_TIME = 4000L
    }


}