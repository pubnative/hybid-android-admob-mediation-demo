package net.pubnative.lite.demo.ui.mRectVideo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import net.pubnative.lite.demo.TabActivity
import net.pubnative.lite.utils.ClipboardUtils
import net.pubnative.lite.demo.BuildConfig
import net.pubnative.lite.demo.databinding.FragmentAdmobMrectVideoBinding

class AdmobMediationMRectVideoFragment : Fragment() {
    val TAG = AdmobMediationMRectVideoFragment::class.java.simpleName

    private lateinit var admobMRect: AdView

    private var _binding: FragmentAdmobMrectVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmobMrectVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adUnitId = BuildConfig.admob_medium_video_ad_unit

        admobMRect = AdView(requireActivity())
        admobMRect.adSize = AdSize.MEDIUM_RECTANGLE
        admobMRect.adUnitId = adUnitId
        admobMRect.adListener = adListener

        _binding?.admobMrectContainer?.addView(admobMRect)

        _binding?.buttonLoad?.setOnClickListener {
            _binding?.viewError?.text = ""
            admobMRect.loadAd(
                AdRequest.Builder()
                    .build()
            )
        }

        _binding?.viewError?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewError?.text.toString()
            )
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