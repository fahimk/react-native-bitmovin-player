package com.takeoffmediareactnativebitmovinplayer

import com.bitmovin.player.BitmovinPlayerView
import com.bitmovin.player.cast.BitmovinCastManager
import com.bitmovin.player.config.PlayerConfiguration
import com.bitmovin.player.config.media.SourceItem
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp


class ReactNativeBitmovinPlayer : SimpleViewManager<BitmovinPlayerView>() {

  private lateinit var _context: ThemedReactContext
  private lateinit var _playerView: BitmovinPlayerView

  override fun createViewInstance(reactContext: ThemedReactContext): BitmovinPlayerView {
    _context = reactContext

    val playerConfiguration = PlayerConfiguration()
    playerConfiguration.playbackConfiguration?.autoplayEnabled = false
    _playerView = BitmovinPlayerView(_context, playerConfiguration)


    // Instantiate a custom FullscreenHandler
    val customFullscreenHandler = CustomFullscreenHandler(_playerView, null)
    // Set the FullscreenHandler to the BitmovinPlayerView
    _playerView.setFullscreenHandler(customFullscreenHandler)

    BitmovinCastManager.getInstance().updateContext(reactContext)

    return _playerView;
  }

  override fun onDropViewInstance(view: BitmovinPlayerView) {
    _playerView.onDestroy()
    super.onDropViewInstance(view)
  }

  override fun getName(): String {
    return "ReactNativeBitmovinPlayer"
  }

  @ReactProp(name = "configuration")
  fun setConfiguration(playerView: BitmovinPlayerView, configProps: ReadableMap?) {
    playerView.player?.load(SourceItem(configProps?.getString("url")))
  }
}
