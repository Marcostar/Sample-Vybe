package com.sagycorp.vybe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sagycorp.vybe.R
import com.sagycorp.vybe.databinding.WebViewFragmentBinding
import com.sagycorp.vybe.utils.CustomWebView

class WebView : Fragment() {

   private lateinit var binding: WebViewFragmentBinding

    private lateinit var viewModel: WebViewViewModel

    val args: WebViewArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.web_view_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WebViewViewModel::class.java)

        binding.webView.loadUrl(viewModel.getURL(args.url))

        binding.apply.setOnClickListener {


            binding.webView.webViewClient = object :WebViewClient()
            {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.loadUrl("javascript:"+ binding.codeEditText.text.toString(), null)
                }
            }
        }

    }

}