package net.pubnative.lite.demo.ui.banner

import net.pubnative.lite.demo.R
import net.pubnative.lite.demo.TabActivity

class AdmobMediationBannerActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationBannerFragment()

    override fun getActivityTitle() = getString(R.string.admob_banner)
}