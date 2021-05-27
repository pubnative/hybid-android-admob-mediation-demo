package net.pubnative.lite.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import net.pubnative.lite.databinding.FragmentNavMediationBinding
import net.pubnative.lite.ui.banner.AdmobMediationBannerActivity
import net.pubnative.lite.ui.interstitial.AdmobMediationInterstitialActivity
import net.pubnative.lite.ui.interstitialVideo.AdmobMediationInterstitialVideoActivity
import net.pubnative.lite.ui.leaderboard.AdmobMediationLeaderboardActivity
import net.pubnative.lite.ui.mRect.AdmobMediationMRectActivity
import net.pubnative.lite.ui.mRectVideo.AdmobMediationMRectVideoActivity
import net.pubnative.lite.ui.native.AdmobMediationNativeActivity
import net.pubnative.lite.ui.rewarded.AdmobMediationRewardedActivity

class AdmobMediationNavFragment : Fragment() {

    private var _binding: FragmentNavMediationBinding? = null
    private val binding get() = _binding!!

    private val PERMISSION_REQUEST = 1000

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

        checkPermissions()

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
            val intent = Intent(activity, AdmobMediationInterstitialVideoActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobRewarded?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationRewardedActivity::class.java)
            startActivity(intent)
        }

        _binding?.buttonAdmobNative?.setOnClickListener {
            val intent = Intent(activity, AdmobMediationNativeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkPermissions() {
        activity?.let {
            if (ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(it, permissionList, PERMISSION_REQUEST)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST -> {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    activity?.apply {
                        Toast.makeText(
                            this,
                            "Location permission denied. You can change this on the app settings.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}