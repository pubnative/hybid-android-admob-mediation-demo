package net.pubnative.lite.demo.ui.interstitial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.utils.ClipboardUtils
import net.pubnative.lite.demo.BuildConfig
import net.pubnative.lite.demo.databinding.FragmentAdmobInterstitialBinding

class AdmobMediationInterstitialFragment : Fragment() {

    val TAG = AdmobMediationInterstitialFragment::class.java.simpleName

    private var admobInterstitial: InterstitialAd? = null
    private var _binding: FragmentAdmobInterstitialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmobInterstitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonShow?.isEnabled = false

        val adUnitId = BuildConfig.admob_interstitial_ad_unit

        _binding?.buttonLoad?.setOnClickListener {
            _binding?.viewError?.text = ""
            InterstitialAd.load(
                requireActivity(),
                adUnitId,
                AdRequest.Builder().build(),
                adLoadCallback
            )
        }

        _binding?.buttonShow?.setOnClickListener {
            admobInterstitial?.show(requireActivity())
        }

        _binding?.viewError?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewError?.text.toString()
            )
        }
    }

    // ---------------- Admob Ad Load Listener ---------------------
    private val adLoadCallback = object : InterstitialAdLoadCallback() {
        override fun onAdLoaded(interstitialAd: InterstitialAd) {
            super.onAdLoaded(interstitialAd)
            admobInterstitial = interstitialAd
            admobInterstitial?.fullScreenContentCallback = fullScreenContentCallback
            Log.d(TAG, "onAdLoaded")
            displayLogs()
            _binding?.buttonShow?.isEnabled = true
        }

        override fun onAdFailedToLoad(error: LoadAdError) {
            super.onAdFailedToLoad(error)
            Log.d(TAG, "onAdFailedToLoad")
            admobInterstitial = null
            _binding?.buttonShow?.isEnabled = false
            displayLogs()
            _binding?.viewError?.text = error.message
        }
    }

    // ---------------- Admob fullscreen Listener ---------------------
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
            admobInterstitial = null
            _binding?.buttonShow?.isEnabled = false
        }

        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Log.d(TAG, "onAdFailedToLoad")
            admobInterstitial = null
            _binding?.buttonShow?.isEnabled = false
        }
    }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}