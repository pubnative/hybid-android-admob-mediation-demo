package net.pubnative.lite.ui.banner

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationBannerActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationBannerFragment()

    override fun getActivityTitle() = getString(R.string.admob_banner)
}