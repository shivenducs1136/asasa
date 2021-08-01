package com.example.zomatoapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.zomatoapp.R
import com.example.zomatoapp.databinding.FragmentDiscoverRestrauntBinding

class DiscoverRestrauntFragment : Fragment() {
    lateinit var binding: FragmentDiscoverRestrauntBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover_restraunt,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundleurl=this.arguments
        var url:String=""

        if (bundleurl!=null){
            url=bundleurl.getString("url").toString()

        }

        binding.restrauntD.webViewClient = WebViewClient()
        binding.restrauntD.loadUrl(url)
        binding.restrauntD.settings.javaScriptEnabled = true
        binding.restrauntD.settings.setSupportZoom(true)

        binding.webviewBack.setOnClickListener {
            findNavController().navigate(R.id.action_discoverRestrauntFragment_to_homeFragment)
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.webviewDProgress.visibility = View.GONE
        }
    }
}

