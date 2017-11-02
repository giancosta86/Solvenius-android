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

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager

class MainActivity : Activity() {
    private var webView: GameWebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setupWebView()
    }


    private fun setupWebView() {
        webView = GameWebView(this)

        setContentView(webView)

        webView!!.loadUrl(getString(R.string.game_url))
    }


    override fun onPause() {
        webView!!.pauseMusic()

        super.onPause()
    }


    override fun onResume() {
        super.onResume()

        webView!!.playMusic()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    webView!!.handleBackButton()
                    return true
                }
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}
