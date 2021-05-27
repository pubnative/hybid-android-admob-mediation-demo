package net.pubnative.hybid.adapters.admob.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.pubnative.lite.databinding.FragmentNavMediationBinding
import net.pubnative.lite.ui.banner.AdmobMediationBannerActivity
import net.pubnative.lite.ui.interstitial.AdmobMediationInterstitialActivity
import net.pubnative.lite.ui.leaderboard.AdmobMediationLeaderboardActivity
import net.pubnative.lite.ui.mRect.AdmobMediationMRectActivity
import net.pubnative.lite.ui.mRectVideo.AdmobMediationMRectVideoActivity

class AdmobMediationNavFragment : Fragment() {

    private var _binding: FragmentNavMediationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavMediationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonAdmobBanner?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationBannerActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobMedium?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationMRectActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobMediumVideo?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationMRectVideoActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobLeaderboard?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationLeaderboardActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobInterstitial?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationInterstitialActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobInterstitialVideo?.setOnClickListener {

        }

        _binding?.buttonAdmobRewarded?.setOnClickListener {

        }

        _binding?.buttonAdmobNative?.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}