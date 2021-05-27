package net.pubnative.lite.ui.rewarded

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationRewardedActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationRewardedFragment()

    override fun getActivityTitle() = getString(R.string.admob_rewarded)
}