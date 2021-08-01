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
import com.example.zomatoapp.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    lateinit var binding:FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_web,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundleurl=this.arguments

        var rurl:String=""
        if (bundleurl!=null){

            rurl=bundleurl.getString("Rurl").toString()
        }


       binding.wvRestrauntD.webViewClient=WebViewClient()
        binding.wvRestrauntD.loadUrl(rurl)
        binding.wvRestrauntD.settings.javaScriptEnabled = true
        binding.wvRestrauntD.settings.setSupportZoom(true)

       // binding.wvWebviewBack.setOnClickListener {
        //    findNavController().navigate(R.id.action_webFragment_to_homeFragment)
       // }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.wvWebviewDProgress.visibility = View.GONE
        }
    }
}

