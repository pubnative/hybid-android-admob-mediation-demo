package net.pubnative.hybid.adapters.admob.ui.leaderboard

import net.pubnative.hybid.adapters.admob.R
import net.pubnative.hybid.adapters.admob.ui.TabActivity

class AdmobMediationLeaderboardActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationLeaderboardFragment()

    override fun getActivityTitle() = getString(R.string.admob_leaderboard)
}