package com.takeoffmediareactnativebitmovinplayer

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.ui.FullscreenHandler
class MyFullscreenHandler(
  private val playerView: BitmovinPlayerView
) : FullscreenHandler {

  fun closeActivity() {
    val context = playerView.context;
    (context as Activity).finish();
  }
  override fun onFullscreenRequested() {
    closeActivity();
  }

  override fun onFullscreenExitRequested() {
    closeActivity();
  }

  override fun onResume() {
  }

  override fun onPause() {}

  override fun onDestroy() {
  }

  override fun isFullScreen(): Boolean {
    return true
  }

}
