package net.pubnative.lite.demo.ui.interstitial

import net.pubnative.lite.demo.R
import net.pubnative.lite.demo.TabActivity

class AdmobMediationInterstitialActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationInterstitialFragment()

    override fun getActivityTitle() = getString(R.string.admob_interstitial)
}