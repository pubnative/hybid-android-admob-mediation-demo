package net.pubnative.lite.demo.ui.rewarded

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationRewardedActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationRewardedFragment()

    override fun getActivityTitle() = getString(R.string.admob_rewarded)
}