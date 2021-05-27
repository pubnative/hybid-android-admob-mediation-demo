package net.pubnative.lite.ui.interstitialVideo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.BuildConfig
import net.pubnative.lite.databinding.FragmentAdmobInterstitialVideoBinding
import net.pubnative.lite.utils.AdmobErrorParser
import net.pubnative.lite.utils.ClipboardUtils

class AdmobMediationInterstitialVideoFragment : Fragment() {

    val TAG = AdmobMediationInterstitialVideoFragment::class.java.simpleName

    private lateinit var admobInterstitial: InterstitialAd

    private var _binding: FragmentAdmobInterstitialVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmobInterstitialVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonShow?.isEnabled = false

        val adUnitId = BuildConfig.admob_interstitial_video_ad_unit

        admobInterstitial = InterstitialAd(activity)
        admobInterstitial.adUnitId = adUnitId
        admobInterstitial.adListener = adListener

        _binding?.buttonLoad?.setOnClickListener {
            _binding?.viewError?.text = ""
            admobInterstitial.loadAd(
                AdRequest.Builder()
                    .addTestDevice("9CD3F3CADFC5127409B07C5F802273E7")
                    .build()
            )
        }

        _binding?.buttonShow?.setOnClickListener {
            admobInterstitial.show()
        }

        _binding?.viewError?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewError?.text.toString()
            )
        }
    }

    // ---------------- Admob Ad Listener ---------------------
    private val adListener = object : AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            Log.d(TAG, "onAdLoaded")
            displayLogs()
            _binding?.buttonShow?.isEnabled = true
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            Log.d(TAG, "onAdFailedToLoad")
            displayLogs()
            _binding?.viewError?.text = AdmobErrorParser.getErrorMessage(errorCode)
        }

        override fun onAdClicked() {
            super.onAdClicked()
            Log.d(TAG, "onAdClicked")
        }

        override fun onAdOpened() {
            super.onAdOpened()
            Log.d(TAG, "onAdOpened")
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            Log.d(TAG, "onAdLeftApplication")
        }

        override fun onAdClosed() {
            super.onAdClosed()
            Log.d(TAG, "onAdClosed")
        }
    }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}