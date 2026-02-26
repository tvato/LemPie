package eu.tvato.lempie

import android.app.Application
import android.content.Context
import androidx.lifecycle.lifecycleScope
import eu.tvato.lempie.utils.CurrentSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LempieApplication: Application() {
    companion object{
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}