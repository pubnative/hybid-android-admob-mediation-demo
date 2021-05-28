package net.pubnative.lite.demo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.pubnative.lite.utils.ClipboardUtils
import net.pubnative.lite.utils.JsonUtils
import net.pubnative.lite.demo.databinding.FragmentDebugBinding
import net.pubnative.lite.utils.AdRequestRegistry

class DebugFragment : Fragment() {

    private var _binding: FragmentDebugBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebugBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.viewRequestUrl?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewRequestUrl?.text.toString()
            )
        }

        _binding?.viewLatency?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewLatency?.text.toString()
            )
        }

        _binding?.viewResponse?.setOnClickListener {
            ClipboardUtils.copyToClipboard(
                requireActivity(),
                _binding?.viewResponse?.text.toString()
            )
        }
    }

    fun cleanLogs() {
        _binding?.viewRequestUrl?.text = ""
        _binding?.viewLatency?.text = ""
        _binding?.viewResponse?.text = ""
    }

    fun updateLogs() {
        val registryItem = AdRequestRegistry.getInstance().lastAdRequest
        if (registryItem != null) {
            _binding?.viewRequestUrl?.text = registryItem.url
            _binding?.viewLatency?.text = registryItem.latency.toString()

            if (!TextUtils.isEmpty(registryItem.response)) {
                _binding?.viewResponse?.text = JsonUtils.toFormattedJson(registryItem.response)
            }
        }
        AdRequestRegistry.getInstance().setLastAdRequest("", "", 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}