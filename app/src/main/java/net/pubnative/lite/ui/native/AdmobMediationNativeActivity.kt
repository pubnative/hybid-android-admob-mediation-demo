package net.pubnative.lite.ui.native

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationNativeActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationNativeFragment()

    override fun getActivityTitle() = getString(R.string.admob_native)
}