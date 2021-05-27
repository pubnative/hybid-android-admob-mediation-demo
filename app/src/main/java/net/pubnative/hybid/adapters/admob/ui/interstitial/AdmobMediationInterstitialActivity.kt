package net.pubnative.hybid.adapters.admob.ui.interstitial

import net.pubnative.hybid.adapters.admob.R
import net.pubnative.hybid.adapters.admob.TabActivity

class AdmobMediationInterstitialActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationInterstitialFragment()

    override fun getActivityTitle() = getString(R.string.admob_interstitial)
}