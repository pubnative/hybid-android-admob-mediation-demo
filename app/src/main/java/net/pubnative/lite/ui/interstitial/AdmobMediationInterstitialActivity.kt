package net.pubnative.lite.ui.interstitial

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationInterstitialActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationInterstitialFragment()

    override fun getActivityTitle() = getString(R.string.admob_interstitial)
}