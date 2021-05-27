package net.pubnative.lite.ui.rewarded

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.BuildConfig
import net.pubnative.lite.databinding.FragmentAdmobRewardedBinding
import net.pubnative.lite.utils.AdmobErrorParser
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

        admobRewarded = RewardedAd(requireActivity(), adUnitId)

        val adLoadCallback = object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                Log.d(TAG, "onRewardedAdLoaded")
                displayLogs()
                Toast.makeText(context, "Rewarded Ad Loaded", Toast.LENGTH_SHORT).show()
                _binding?.buttonShow?.isEnabled = true
            }

            override fun onRewardedAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, "onRewardedAdFailedToLoad")
                displayLogs()
                Toast.makeText(context, "Rewarded Ad Failed to Load", Toast.LENGTH_SHORT).show()
                _binding?.viewError?.text = AdmobErrorParser.getErrorMessage(adError.code)
            }
        }

        val rewardedAdCallback = object : RewardedAdCallback() {
            override fun onRewardedAdOpened() {
                super.onRewardedAdOpened()
                Log.d(TAG, "onRewardedAdOpened")
            }

            override fun onRewardedAdClosed() {
                super.onRewardedAdClosed()
                Log.d(TAG, "onRewardedAdClosed")
                _binding?.buttonShow?.isEnabled = false
                admobRewarded = null
            }

            override fun onUserEarnedReward(@NonNull reward: RewardItem) {
                Log.d(TAG, "onUserEarnedReward")
            }

            override fun onRewardedAdFailedToShow(adError: AdError) {
                super.onRewardedAdFailedToShow(adError)
                Log.d(TAG, "onRewardedAdFailedToShow")
                _binding?.viewError?.text = AdmobErrorParser.getErrorMessage(adError.code)
            }
        }

        _binding?.buttonLoad?.setOnClickListener {
            _binding?.viewError?.text = ""
            loadRewardedAd(adUnitId, adLoadCallback)
        }

        _binding?.buttonShow?.setOnClickListener {
            showRewardedAd(rewardedAdCallback)
        }

        _binding?.viewError?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewError?.text.toString()
            )
        }
    }

    private fun loadRewardedAd(
        adUnitId: String,
        adLoadCallback: RewardedAdLoadCallback
    ) {
        admobRewarded = RewardedAd(requireActivity(), adUnitId)
        admobRewarded?.loadAd(
            AdRequest.Builder()
                .build(), adLoadCallback
        )
    }

    private fun showRewardedAd(rewardedAdCallback: RewardedAdCallback) {
        if (admobRewarded != null && admobRewarded!!.isLoaded) {
            admobRewarded?.show(requireActivity(), rewardedAdCallback)
        }
    }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}