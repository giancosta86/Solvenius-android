/*^
  ===========================================================================
  Solvenius
  ===========================================================================
  Copyright (C) 2001-2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.solvenius

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


internal class GameWebViewClient : WebViewClient() {
    @SuppressWarnings("deprecation")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return handleUrl(view, url)
    }


    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return handleUrl(view, request.url.toString())
    }


    private fun handleUrl(view: WebView, url: String): Boolean {
        val context = view.context

        val isLocalUrl = url.startsWith("file://") || url.startsWith("javascript:")

        if (!isLocalUrl) {
            when (url) {
                context.getString(R.string.facebook_page_url) -> {

                    context.startActivity(
                            CustomIntents.viewInFacebook(
                                    context.getString(R.string.facebook_page_id),
                                    context.getString(R.string.facebook_page_path)
                            )
                    )

                    return true
                }

                else -> {
                    view.context.startActivity(
                            CustomIntents.view(url)
                    )

                    return true
                }
            }
        }

        return false
    }


    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

        if (url == view.context.getString(R.string.game_url)) {
            (view as GameWebView).playMusic()
        }
    }
}
