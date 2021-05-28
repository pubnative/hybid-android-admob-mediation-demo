package net.pubnative.lite.demo.ui.mRectVideo

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationMRectVideoActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectVideoFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium_video)
}