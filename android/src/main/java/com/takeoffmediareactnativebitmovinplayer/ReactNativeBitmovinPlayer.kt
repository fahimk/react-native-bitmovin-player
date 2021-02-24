package com.takeoffmediareactnativebitmovinplayer

import android.util.Log
import com.bitmovin.player.BitmovinPlayer
import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.config.PlayerConfiguration
import com.bitmovin.player.config.media.SourceItem
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.views.image.ReactImageView


class ReactNativeBitmovinPlayer : SimpleViewManager<BitmovinPlayerView>() {

  private lateinit var globalContext: ThemedReactContext
  private lateinit var playerView: BitmovinPlayerView
  private lateinit var player: BitmovinPlayer

  override fun createViewInstance(reactContext: ThemedReactContext): BitmovinPlayerView {
    Log.i("context: ", reactContext.toString())
    globalContext = reactContext
    playerView = BitmovinPlayerView(globalContext)
    player = playerView.player;

    return playerView;
  }

  override fun onDropViewInstance(view: BitmovinPlayerView) {
    playerView.onDestroy()
    super.onDropViewInstance(view)

  }

  override fun getName(): String {
    return "ReactNativeBitmovinPlayer"
  }

  @ReactProp(name = "configuration")
  fun setConfiguration(playerView: BitmovinPlayerView, configProps: ReadableMap?) {
    val config = PlayerConfiguration()
    config.sourceItem = SourceItem(configProps?.getString("url"))
    playerView.player.setup(config)
  }
}
