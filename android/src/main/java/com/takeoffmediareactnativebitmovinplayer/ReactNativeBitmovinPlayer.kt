package com.takeoffmediareactnativebitmovinplayer

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

  override fun createViewInstance(reactContext: ThemedReactContext): BitmovinPlayerView {
    context = reactContext

    val playerConfiguration = PlayerConfiguration()
    playerConfiguration.playbackConfiguration?.autoplayEnabled = false
    this.playerView = BitmovinPlayerView(this.context, playerConfiguration)


    // Instantiate a custom FullscreenHandler
    val customFullscreenHandler = CustomFullscreenHandler(this.playerView)
    // Set the FullscreenHandler to the BitmovinPlayerView
    this.playerView.setFullscreenHandler(customFullscreenHandler)

    BitmovinCastManager.getInstance().updateContext(reactContext)

    return this.playerView;
  }

  override fun onDropViewInstance(view: BitmovinPlayerView) {
    this.playerView.onDestroy()
    super.onDropViewInstance(view)
  }

  override fun getName(): String {
    return "ReactNativeBitmovinPlayer"
  }

  override fun onHostResume() {
    this.playerView.onResume()
  }

  override fun onHostPause() {
    this.playerView.onPause()
  }

  override fun onHostDestroy() {
    this.playerView.onDestroy()
  }

  @ReactProp(name = "configuration")
  fun setConfiguration(playerView: BitmovinPlayerView, configProps: ReadableMap?) {
    playerView.player?.load(SourceItem(configProps?.getString("url")))
  }
}
