#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ReactNativeBitmovinPlayer, RCTViewManager)

RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(play)
RCT_EXTERN_METHOD(pause)
RCT_EXTERN_METHOD(seekBackwardCommand)
RCT_EXTERN_METHOD(seekForwardCommand)

RCT_EXPORT_VIEW_PROPERTY(autoPlay, BOOL)
RCT_EXPORT_VIEW_PROPERTY(hasZoom, BOOL)
RCT_EXPORT_VIEW_PROPERTY(deviceZoom, BOOL)
RCT_EXPORT_VIEW_PROPERTY(systemUi, BOOL)
RCT_EXPORT_VIEW_PROPERTY(onLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onEvent, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPlaying, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPause, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onSeek, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onForward, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onRewind, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(configuration, NSDictionary);
RCT_EXPORT_VIEW_PROPERTY(analytics, NSDictionary);

@end
