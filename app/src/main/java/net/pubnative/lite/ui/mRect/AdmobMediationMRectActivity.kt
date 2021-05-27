package net.pubnative.lite.ui.mRect

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationMRectActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium)
}