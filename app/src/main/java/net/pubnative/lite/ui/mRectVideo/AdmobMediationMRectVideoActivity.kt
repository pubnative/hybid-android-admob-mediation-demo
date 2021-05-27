package net.pubnative.lite.ui.mRectVideo

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationMRectVideoActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectVideoFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium_video)
}