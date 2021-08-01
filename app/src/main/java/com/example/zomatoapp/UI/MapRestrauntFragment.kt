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
import com.example.zomatoapp.databinding.FragmentMapRestrauntBinding


class MapRestrauntFragment : Fragment() {
    lateinit var binding: FragmentMapRestrauntBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_map_restraunt,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bundlemap=this.arguments
        var urlm:String=""
        if(bundlemap!=null){
            urlm=bundlemap.getString("urlM").toString()

        }
        binding.restrauntM.webViewClient= WebViewClient()
        binding.restrauntM.loadUrl(urlm)
        binding.restrauntM.settings.javaScriptEnabled = true
        binding.restrauntM.settings.setSupportZoom(true)
        binding.webviewBackM.setOnClickListener {
            findNavController().navigate(R.id.action_mapRestrauntFragment_to_mapFragment2)
        }

    }
    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.webviewMapProgress.visibility = View.GONE
        }
    }
}