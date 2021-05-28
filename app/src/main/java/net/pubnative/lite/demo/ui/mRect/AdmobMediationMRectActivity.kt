package net.pubnative.lite.demo.ui.mRect

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationMRectActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium)
}