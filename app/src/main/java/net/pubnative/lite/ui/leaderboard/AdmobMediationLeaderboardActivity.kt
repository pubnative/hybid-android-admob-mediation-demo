package net.pubnative.lite.ui.leaderboard

import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.R

class AdmobMediationLeaderboardActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationLeaderboardFragment()

    override fun getActivityTitle() = getString(R.string.admob_leaderboard)
}