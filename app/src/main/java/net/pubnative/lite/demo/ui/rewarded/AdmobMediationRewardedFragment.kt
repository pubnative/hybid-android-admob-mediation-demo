package net.pubnative.lite.demo.ui.rewarded

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.BuildConfig
import net.pubnative.lite.demo.databinding.FragmentAdmobRewardedBinding
import net.pubnative.lite.utils.ClipboardUtils

class AdmobMediationRewardedFragment : Fragment() {
    val TAG = AdmobMediationRewardedFragment::class.java.simpleName

    private var admobRewarded: RewardedAd? = null

    private var _binding: FragmentAdmobRewardedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmobRewardedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adUnitId = BuildConfig.admob_rewarded_ad_unit

        _binding?.buttonShow?.isEnabled = false

        _binding?.buttonLoad?.setOnClickListener {
            _binding?.viewError?.text = ""
            loadRewardedAd(adUnitId)
        }

        _binding?.buttonShow?.setOnClickListener {
            showRewardedAd()
        }

        _binding?.viewError?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewError?.text.toString()
            )
        }
    }

    private fun loadRewardedAd(adUnitId: String) {
        RewardedAd.load(requireActivity(), adUnitId, AdRequest.Builder().build(), adLoadCallback)
    }

    private fun showRewardedAd() {
        admobRewarded?.show(requireActivity(), rewardListener)
    }

    private val adLoadCallback = object : RewardedAdLoadCallback() {
        override fun onAdLoaded(ad: RewardedAd) {
            super.onAdLoaded(ad)
            admobRewarded = ad
            admobRewarded?.fullScreenContentCallback = fullScreenContentCallback
            Log.d(TAG, "onAdLoaded")
            displayLogs()
            Toast.makeText(context, "Rewarded Ad Loaded", Toast.LENGTH_SHORT).show()
            _binding?.buttonShow?.isEnabled = true
        }

        override fun onAdFailedToLoad(error: LoadAdError) {
            super.onAdFailedToLoad(error)
            Log.d(TAG, "onAdFailedToLoad")
            admobRewarded = null
            _binding?.buttonShow?.isEnabled = false
            displayLogs()
            Toast.makeText(context, "Rewarded Ad Failed to Load", Toast.LENGTH_SHORT).show()
            _binding?.viewError?.text = error.message
        }
    }

    private val fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdImpression() {
            super.onAdImpression()
            Log.d(TAG, "onAdImpression")
        }

        override fun onAdClicked() {
            super.onAdClicked()
            Log.d(TAG, "onAdClicked")
        }

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
            Log.d(TAG, "onAdShowedFullScreenContent")
        }

        override fun onAdFailedToShowFullScreenContent(error: AdError) {
            super.onAdFailedToShowFullScreenContent(error)
            Log.d(TAG, "onAdFailedToShowFullScreenContent")
            Toast.makeText(requireActivity(), error.message, Toast.LENGTH_LONG).show()
            admobRewarded = null
            _binding?.buttonShow?.isEnabled = false
        }

        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Log.d(TAG, "onAdFailedToLoad")
            admobRewarded = null
            _binding?.buttonShow?.isEnabled = false
        }
    }

    private val rewardListener =
        OnUserEarnedRewardListener { Log.d(TAG, "onUserEarnedReward") }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}