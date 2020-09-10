package com.sagycorp.vybe.ui

import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun getURL(webUrl: String): String {
        var url = webUrl
        if(!url.startsWith("www.")&& !url.startsWith("http://")){
            url = "www.$url"
        }
        if(!url.startsWith("http://")){
            url = "https://$url"
        }
        return url
    }
}