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


import android.app.AlertDialog
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

internal class GameWebChromeClient : WebChromeClient() {
    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        val dialog = AlertDialog.Builder(view.context)
                .setTitle(view.title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()

        dialog.show()
        result.confirm()

        return true
    }


    override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
        AlertDialog.Builder(view.context)
                .setTitle(view.title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    result.confirm()
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    result.cancel()
                }
                .create()
                .show()

        return true
    }
}
