package com.takeoffmediareactnativebitmovinplayer

import android.content.pm.PackageManager
import android.util.Log
import com.bitmovin.analytics.BitmovinAnalyticsConfig
import com.bitmovin.analytics.bitmovin.player.BitmovinPlayerCollector
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.cast.BitmovinCastManager
import com.bitmovin.player.config.PlayerConfiguration
import com.bitmovin.player.config.media.SourceItem
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp


class ReactNativeBitmovinPlayer : SimpleViewManager<BitmovinPlayerView>(), LifecycleEventListener {
  private lateinit var context: ThemedReactContext
  private lateinit var playerView: BitmovinPlayerView
  private lateinit var analyticsCollector: BitmovinPlayerCollector
  private var wasPlayingOnPause = false

  override fun createViewInstance(reactContext: ThemedReactContext): BitmovinPlayerView {
    this.context = reactContext
    this.context.addLifecycleEventListener(this)

    val playerConfiguration = PlayerConfiguration()
    playerConfiguration.playbackConfiguration?.autoplayEnabled = false
    this.playerView = BitmovinPlayerView(this.context, playerConfiguration)

    // analytics
    val app = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
    val bundle = app.metaData
    val bitmovinAnalyticsConfig = BitmovinAnalyticsConfig(bundle.getString("BITMOVIN_ANALYTICS_LICENSE_KEY"))
    this.analyticsCollector = BitmovinPlayerCollector(bitmovinAnalyticsConfig, this.context)
    analyticsCollector.attachPlayer(this.playerView.player);

    // full screen
    val customFullscreenHandler = CustomFullscreenHandler(this.playerView)
    this.playerView.setFullscreenHandler(customFullscreenHandler)

    // casting
    BitmovinCastManager.getInstance().updateContext(reactContext)

    return this.playerView;
  }

  override fun onDropViewInstance(view: BitmovinPlayerView) {
    context.removeLifecycleEventListener(this)
    analyticsCollector.detachPlayer()
    this.playerView.onDestroy()
    super.onDropViewInstance(view)
  }

  override fun getName(): String {
    return "ReactNativeBitmovinPlayer"
  }

  override fun onHostResume() {
    this.playerView.onResume()
    if(wasPlayingOnPause) {
      this.playerView.player?.play()
    }
  }

  override fun onHostPause() {
    Log.i("bitmoviin", "pause")
    this.playerView.onPause()

    if(this.playerView.player?.isPlaying == true) {
      wasPlayingOnPause = true
      this.playerView.player?.pause()
    }
    else {
      wasPlayingOnPause = false
    }
  }

  override fun onHostDestroy() {
    this.playerView.onDestroy()
  }

  @ReactProp(name = "configuration")
  fun setConfiguration(playerView: BitmovinPlayerView, configProps: ReadableMap?) {
    playerView.player?.load(SourceItem(configProps?.getString("url")))
  }
}
