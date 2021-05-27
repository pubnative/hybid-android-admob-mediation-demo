package net.pubnative.hybid.adapters.admob.banner

import net.pubnative.hybid.adapters.admob.R
import net.pubnative.hybid.adapters.admob.TabActivity


class AdmobMediationBannerActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationBannerFragment()

    override fun getActivityTitle() = getString(R.string.admob_banner)
}