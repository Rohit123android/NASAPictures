package com.compoundgrow.app.webviewclient

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URL

class AgentLoginWebviewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val host=Uri.parse(request?.url.toString()).host
        return super.shouldOverrideUrlLoading(view, request)
    }
}