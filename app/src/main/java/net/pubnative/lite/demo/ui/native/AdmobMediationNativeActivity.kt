package net.pubnative.lite.demo.ui.native

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationNativeActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationNativeFragment()

    override fun getActivityTitle() = getString(R.string.admob_native)
}