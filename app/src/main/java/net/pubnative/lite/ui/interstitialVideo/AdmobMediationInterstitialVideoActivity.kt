package net.pubnative.lite.ui.interstitialVideo

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationInterstitialVideoActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationInterstitialVideoFragment()

    override fun getActivityTitle() = getString(R.string.admob_interstitial_video)
}