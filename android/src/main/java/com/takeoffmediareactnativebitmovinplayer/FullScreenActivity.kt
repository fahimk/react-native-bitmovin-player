package com.takeoffmediareactnativebitmovinplayer

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bitmovin.player.BitmovinPlayer
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.cast.BitmovinCastManager
import com.bitmovin.player.config.PlayerConfiguration
import com.bitmovin.player.config.media.SourceItem
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {

  private var bitmovinPlayer: BitmovinPlayer? = null
  private var bitmovinPlayerView: BitmovinPlayerView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen)


    val playerConfiguration = PlayerConfiguration()
    playerConfiguration.playbackConfiguration?.autoplayEnabled = true
    this.bitmovinPlayerView = BitmovinPlayerView(this, playerConfiguration)
    this.bitmovinPlayer = this.bitmovinPlayerView?.player
    this.bitmovinPlayer?.load(intent.getParcelableExtra<SourceItem>("sourceItem"))

    this.bitmovinPlayerView?.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    root.addView(this.bitmovinPlayerView, 0)
  }

  override fun onStart() {
    super.onStart()
    this.bitmovinPlayerView?.onStart()
  }

  override fun onResume() {
    super.onResume()
    this.bitmovinPlayerView?.onResume()
  }

  override fun onPause() {
    this.bitmovinPlayerView?.onPause()
    super.onPause()
  }

  override fun onStop() {
    this.bitmovinPlayerView?.onStop()
    super.onStop()
  }

  override fun onDestroy() {
    this.bitmovinPlayerView?.onDestroy()
    super.onDestroy()
  }
}
