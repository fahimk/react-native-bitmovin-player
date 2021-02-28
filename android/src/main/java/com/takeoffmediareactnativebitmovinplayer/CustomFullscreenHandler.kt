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
class CustomFullscreenHandler(
  private val playerView: BitmovinPlayerView
) : FullscreenHandler {

  override fun onFullscreenRequested() {
    val context = playerView.context;
    val intent = Intent(context, FullScreenActivity::class.java)
    intent.putExtra("sourceItem", playerView.player?.config?.sourceItem)

    context.startActivity(intent);
  }

  override fun onFullscreenExitRequested() {
  }

  override fun onResume() {
  }

  override fun onPause() {}

  override fun onDestroy() {
  }

  override fun isFullScreen(): Boolean {
    return false
  }

}
