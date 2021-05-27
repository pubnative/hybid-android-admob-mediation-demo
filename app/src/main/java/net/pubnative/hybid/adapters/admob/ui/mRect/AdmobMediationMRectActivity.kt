package net.pubnative.hybid.adapters.admob.ui.mRect

import net.pubnative.hybid.adapters.admob.R
import net.pubnative.hybid.adapters.admob.ui.TabActivity


class AdmobMediationMRectActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationMRectFragment()

    override fun getActivityTitle() = getString(R.string.admob_medium)
}