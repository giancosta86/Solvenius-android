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

import android.content.Intent
import android.net.Uri

internal object CustomIntents {
    fun viewInFacebook(pageId: String, pagePath: String): Intent {
        try {
            return view("fb://page/" + pageId)
        } catch (e: Exception) {
            return view("https://www.facebook.com/" + pagePath)
        }
    }


    fun view(url: String): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
