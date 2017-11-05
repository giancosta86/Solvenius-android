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

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.webkit.WebView


internal class GameWebView(private val mainActivity: Activity) : WebView(mainActivity) {
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupBrowsing() {
        settings.userAgentString = "Solvenius App"
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true

        webViewClient = GameWebViewClient()
        webChromeClient = GameWebChromeClient()
    }


    fun handleBackButton() {
        if (canGoBack()) {
            goBack()
        } else {
            evaluateJavascript("tryToGoBack()") { result ->
                if (result == "null") {
                    AlertDialog.Builder(mainActivity)
                            .setMessage("Exit the app?")
                            .setPositiveButton("Exit") { _, _ ->
                                mainActivity.finish()
                            }
                            .setNegativeButton("Stay", null)
                            .show()
                }
            }
        }
    }


    fun playMusic() {
        loadUrl("javascript:playMusic();")
    }


    fun pauseMusic() {
        loadUrl("javascript:pauseMusic();")
    }


    init {
        setupBrowsing()
    }
}
