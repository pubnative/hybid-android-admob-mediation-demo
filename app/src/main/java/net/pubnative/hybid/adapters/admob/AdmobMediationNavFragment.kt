package net.pubnative.hybid.adapters.admob

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.pubnative.hybid.adapters.admob.databinding.FragmentNavMediationBinding
import net.pubnative.hybid.adapters.admob.ui.banner.AdmobMediationBannerActivity
import net.pubnative.hybid.adapters.admob.ui.interstitial.AdmobMediationInterstitialActivity
import net.pubnative.hybid.adapters.admob.ui.mRect.AdmobMediationMRectActivity

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
        }

        _binding?.buttonAdmobLeaderboard?.setOnClickListener {

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

//        view.findViewById<Button>(R.id.button_admob_medium).setOnClickListener {

//        }
//
//        view.findViewById<Button>(R.id.button_admob_medium_video).setOnClickListener {
//            val intent = Intent(activity, AdmobMediationMRectVideoActivity::class.java)
//            startActivity(intent)
//        }
//
//        view.findViewById<Button>(R.id.button_admob_leaderboard).setOnClickListener {
//            val intent = Intent(activity, AdmobMediationLeaderboardActivity::class.java)
//            startActivity(intent)
//        }
//
//        view.findViewById<Button>(R.id.button_admob_interstitial).setOnClickListener {
//            val intent = Intent(activity, AdmobMediationInterstitialActivity::class.java)
//            startActivity(intent)
//        }
//
//        view.findViewById<Button>(R.id.button_admob_interstitial_video).setOnClickListener {
//            val intent = Intent(activity, AdmobMediationInterstitialVideoActivity::class.java)
//            startActivity(intent)
//        }
//
//        view.findViewById<Button>(R.id.button_admob_rewarded).setOnClickListener {
//            val intent = Intent(activity, AdmobMediationRewardedActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}