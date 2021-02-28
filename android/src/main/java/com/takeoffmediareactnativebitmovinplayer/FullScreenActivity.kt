package com.takeoffmediareactnativebitmovinplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitmovin.player.BitmovinPlayer
import com.bitmovin.player.config.media.SourceItem
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {

  private var bitmovinPlayer: BitmovinPlayer? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen)

    this.bitmovinPlayer = bitmovinPlayerView.player

    this.initializePlayer()
  }

  override fun onStart() {
    super.onStart()
    bitmovinPlayerView.onStart()
  }

  override fun onResume() {
    super.onResume()
    bitmovinPlayerView.onResume()
  }

  override fun onPause() {
    bitmovinPlayerView.onPause()
    super.onPause()
  }

  override fun onStop() {
    bitmovinPlayerView.onStop()
    super.onStop()
  }

  override fun onDestroy() {
    bitmovinPlayerView.onDestroy()
    super.onDestroy()
  }

  private fun initializePlayer() {
    // load source using a source item
    this.bitmovinPlayer?.load(SourceItem(intent.getStringExtra("sourceItem")))
  }
}
