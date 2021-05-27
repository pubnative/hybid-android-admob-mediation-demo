package net.pubnative.hybid.adapters.admob.ui.mRectVideo

import net.pubnative.hybid.adapters.admob.R
import net.pubnative.hybid.adapters.admob.ui.TabActivity

class AdmobMediationMRectVideoActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectVideoFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium_video)
}