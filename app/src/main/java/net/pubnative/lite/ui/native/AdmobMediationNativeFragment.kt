package net.pubnative.lite.ui.native

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import net.pubnative.hybid.adapters.admob.ui.TabActivity
import net.pubnative.lite.BuildConfig
import net.pubnative.lite.R
import net.pubnative.lite.databinding.FragmentAdmobNativeBinding
import net.pubnative.lite.utils.AdmobErrorParser

class AdmobMediationNativeFragment : Fragment() {

    val TAG = AdmobMediationNativeFragment::class.java.simpleName

    private var admobNative: UnifiedNativeAd? = null

    private var _binding: FragmentAdmobNativeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmobNativeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adUnitId = BuildConfig.admob_native_ad_unit

        _binding?.buttonLoad?.setOnClickListener {
            admobNative?.destroy()
            _binding?.viewError?.text = ""
            val adLoader = AdLoader.Builder(requireContext(), adUnitId)
                .forUnifiedNativeAd {
                    if (isDetached) {
                        it.destroy()
                    } else {
                        admobNative = it
                        renderAd()
                    }
                }
                .withAdListener(adListener)
                .withNativeAdOptions(nativeAdOptions).build()

            adLoader.loadAd(AdRequest.Builder().build())
        }
    }

    override fun onDestroy() {
        admobNative?.destroy()
        super.onDestroy()
    }

    private fun renderAd() {
        admobNative?.let {
            _binding?.adContainer?.removeAllViews()
            val adView = LayoutInflater.from(requireContext())
                .inflate(
                    R.layout.layout_admob_native_ad,
                    _binding?.adContainer,
                    false
                ) as UnifiedNativeAdView
            val adIcon = adView.findViewById<ImageView>(R.id.ad_icon)
            val adTitle = adView.findViewById<TextView>(R.id.ad_title)
            val adBanner = adView.findViewById<ImageView>(R.id.ad_banner)
            val adDescription = adView.findViewById<TextView>(R.id.ad_description)
            val adCallToAction = adView.findViewById<Button>(R.id.ad_call_to_action)
            val adChoices = adView.findViewById<ImageView>(R.id.ad_choices)

            adIcon.setImageDrawable(it.icon?.drawable)
            adView.iconView = adIcon

            it.images?.let { images ->
                if (images.isNotEmpty()) {
                    adBanner.setImageDrawable(it.images.first().drawable)
                    adView.imageView = adBanner
                }
            }

            adTitle.text = it.headline
            adView.headlineView = adTitle
            adDescription.text = it.body
            adView.bodyView = adDescription
            adCallToAction.text = it.callToAction
            adView.callToActionView = adCallToAction
            //adView.advertiserView = adChoices
            adView.setNativeAd(it)

            _binding?.adContainer?.addView(adView)
        }
    }

    private val nativeAdOptions = NativeAdOptions.Builder().build()

    // ------------------ Admob Ad Listener ---------------------
    private val adListener = object : AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            displayLogs()
            Log.d(TAG, "onAdLoaded")
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            super.onAdFailedToLoad(errorCode)
            displayLogs()
            _binding?.viewError?.text = AdmobErrorParser.getErrorMessage(errorCode)
            Log.d(TAG, "onAdFailedToLoad")
        }

        override fun onAdImpression() {
            super.onAdImpression()
            Log.d(TAG, "onAdImpression")
        }

        override fun onAdClicked() {
            super.onAdClicked()
            Log.d(TAG, "onAdClicked")
        }

        override fun onAdOpened() {
            super.onAdOpened()
            Log.d(TAG, "onAdOpened")
        }

        override fun onAdClosed() {
            super.onAdClosed()
            Log.d(TAG, "onAdClosed")
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            Log.d(TAG, "onAdLeftApplication")
        }
    }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}