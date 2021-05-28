package net.pubnative.lite.demo.ui.interstitialVideo

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationInterstitialVideoActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationInterstitialVideoFragment()

    override fun getActivityTitle() = getString(R.string.admob_interstitial_video)
}