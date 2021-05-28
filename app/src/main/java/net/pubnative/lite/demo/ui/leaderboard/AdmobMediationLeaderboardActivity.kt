package net.pubnative.lite.demo.ui.leaderboard

import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.R

class AdmobMediationLeaderboardActivity : TabActivity() {
    override fun getAdFragment() = AdmobMediationLeaderboardFragment()

    override fun getActivityTitle() = getString(R.string.admob_leaderboard)
}