package net.pubnative.lite.demo.ui.native

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
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.demo.BuildConfig
import net.pubnative.lite.demo.R
import net.pubnative.lite.demo.databinding.FragmentAdmobNativeBinding
import net.pubnative.lite.utils.AdmobErrorParser

class AdmobMediationNativeFragment : Fragment() {

    val TAG = AdmobMediationNativeFragment::class.java.simpleName

    private var admobNative: NativeAd? = null
    private val nativeAdOptions = NativeAdOptions.Builder().build()

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
                .forNativeAd { ad ->
                    if (isDetached) {
                        ad.destroy()
                    } else {
                        admobNative = ad
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
                ) as NativeAdView
            val adIcon = adView.findViewById<ImageView>(R.id.ad_icon)
            val adTitle = adView.findViewById<TextView>(R.id.ad_title)
            val adBanner = adView.findViewById<ImageView>(R.id.ad_banner)
            val adDescription = adView.findViewById<TextView>(R.id.ad_description)
            val adCallToAction = adView.findViewById<Button>(R.id.ad_call_to_action)

            adIcon.setImageDrawable(it.icon?.drawable)
            adView.iconView = adIcon

            it.images.let { images ->
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
            adView.setNativeAd(it)

            _binding?.adContainer?.addView(adView)
        }
    }

    // ------------------ Admob Ad Listener ---------------------
    private val adListener = object : AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            displayLogs()
            Log.d(TAG, "onAdLoaded")
        }

        override fun onAdFailedToLoad(error: LoadAdError) {
            super.onAdFailedToLoad(error)
            displayLogs()
            _binding?.viewError?.text = error.message
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
    }

    private fun displayLogs() {
        if (activity != null) {
            val activity = activity as TabActivity
            activity.notifyAdUpdated()
        }
    }
}