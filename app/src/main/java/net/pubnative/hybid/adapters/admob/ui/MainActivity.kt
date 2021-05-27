package net.pubnative.hybid.adapters.admob.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.pubnative.hybid.adapters.admob.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame, AdmobMediationNavFragment())
            .commitAllowingStateLoss()
    }
}