package com.takeoffmediareactnativebitmovinplayer

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bitmovin.analytics.BitmovinAnalyticsConfig
import com.bitmovin.analytics.bitmovin.player.BitmovinPlayerCollector
import com.bitmovin.player.BitmovinPlayer
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.config.PlayerConfiguration
import com.bitmovin.player.config.media.SourceItem
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {

  private lateinit var bitmovinPlayer: BitmovinPlayer
  private lateinit var bitmovinPlayerView: BitmovinPlayerView
  private lateinit var analyticsCollector: BitmovinPlayerCollector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen)


    val playerConfiguration = PlayerConfiguration()
    playerConfiguration.playbackConfiguration?.autoplayEnabled = true
    this.bitmovinPlayerView = BitmovinPlayerView(this, playerConfiguration)
    this.bitmovinPlayer = this.bitmovinPlayerView.player!!
    this.bitmovinPlayer.load(intent.getParcelableExtra<SourceItem>("sourceItem"))
    val customFullscreenHandler = MyFullscreenHandler(this.bitmovinPlayerView)
    this.bitmovinPlayerView.setFullscreenHandler(customFullscreenHandler)
    this.bitmovinPlayerView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    root.addView(this.bitmovinPlayerView, 0)

    // analytics
    val app = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    val bundle = app.metaData
    val bitmovinAnalyticsConfig = BitmovinAnalyticsConfig(bundle.getString("BITMOVIN_ANALYTICS_LICENSE_KEY"))
    this.analyticsCollector = BitmovinPlayerCollector(bitmovinAnalyticsConfig, this)
    analyticsCollector.attachPlayer(this.bitmovinPlayer);
  }

  override fun onStart() {
    super.onStart()
    this.bitmovinPlayerView.onStart()
  }

  override fun onResume() {
    super.onResume()
    this.bitmovinPlayerView.onResume();
  }

  override fun onPause() {
    super.onPause()
    this.bitmovinPlayerView.onPause();
  }

  override fun onStop() {
    this.bitmovinPlayerView.onStop()
    super.onStop()
  }

  override fun onDestroy() {
    analyticsCollector.detachPlayer()
    this.bitmovinPlayerView.onDestroy()
    super.onDestroy()
  }

}
