import React from 'react';
import { NativeModules, requireNativeComponent } from 'react-native';

type ReactNativeBitmovinPlayerType = {
  autoPlay: boolean;
  hasZoom: boolean;
  deviceZoom: boolean;
  systemUi: boolean;
  style?: any;
  onLoad?: (event: any) => void;
  onPlaying?: (event: any) => void;
  onPause?: (event: any) => void;
  onEvent?: (event: any) => void;
  onError?: (event: any) => void;
  onSeek?: (event: any) => void;
  onForward?: (event: any) => void;
  onRewind?: (event: any) => void;
  configuration: {
    url: string;
    poster?: string;
    startOffset: number;
    hasNextEpisode: boolean;
    subtitles?: string;
    thumbnails?: string;
    title?: string;
    subtitle?: string;
    nextPlayback?: number;
    hearbeat?: number;
    advisory?: {
      classification: string;
      description: string;
    };
  };
  analytics?: {
    videoId: string;
    title: string;
    userId: string;
    cdnProvider: string;
    customData1: string;
    customData2: string;
    customData3: string;
  };
};

type ReactNativeBitmovinPlayerMethodsType = {
  ReactNativeBitmovinPlayer: {
    multiply(a: number, b: number): Promise<number>;
    play(): void;
    pause(): void;
    destroy(): void;
    seekBackwardCommand(): void;
    seekForwardCommand(): void;
  };
};

const {
  ReactNativeBitmovinPlayer: ReactNativeBitmovinPlayerIntance,
}: ReactNativeBitmovinPlayerMethodsType = NativeModules as ReactNativeBitmovinPlayerMethodsType;

const ReactNativeBitmovinPlayer = requireNativeComponent<ReactNativeBitmovinPlayerType>(
  'ReactNativeBitmovinPlayer'
);

export { ReactNativeBitmovinPlayerIntance };

export default ({
  autoPlay,
  hasZoom,
  deviceZoom,
  systemUi,
  style,
  onLoad,
  onPlaying,
  onPause,
  onEvent,
  onError,
  onSeek,
  onForward,
  onRewind,
  configuration,
  analytics,
}: ReactNativeBitmovinPlayerType) => {
  const styles = { flex: 1, width: '100%', height: '100%' };
  return (
    <ReactNativeBitmovinPlayer
      {...{
        autoPlay,
        hasZoom,
        deviceZoom,
        systemUi,
        onLoad,
        onPlaying,
        onPause,
        onEvent,
        onError,
        onSeek,
        onRewind,
        onForward,
        configuration,
        analytics,
      }}
      style={[styles, style]}
    />
  );
};
