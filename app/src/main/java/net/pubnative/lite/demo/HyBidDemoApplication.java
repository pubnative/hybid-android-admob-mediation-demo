// The MIT License (MIT)
//
// Copyright (c) 2018 PubNative GmbH
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package net.pubnative.lite.demo;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import net.pubnative.lite.sdk.HyBid;
import net.pubnative.lite.sdk.utils.Logger;
import net.pubnative.lite.sdk.vpaid.enums.AudioState;

/**
 * Created by erosgarciaponte on 08.01.18.
 */

public class HyBidDemoApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initSettings();
    }

    private void initSettings() {
        HyBid.initialize(BuildConfig.app_token, this, success -> {
            Log.d("success", String.valueOf(success));
        });

        HyBid.setLogLevel(Logger.Level.debug);
        HyBid.setTestMode(false);
        HyBid.setCoppaEnabled(true);

        HyBid.setAge("30");
        HyBid.setGender("male");
        HyBid.setLocationTrackingEnabled(true);
        HyBid.setLocationUpdatesEnabled(true);


        HyBid.setVideoAudioStatus(AudioState.DEFAULT);

        HyBid.setInterstitialSkipOffset(4);

        if (HyBid.getViewabilityManager() != null) {
            HyBid.getViewabilityManager().setViewabilityMeasurementEnabled(true);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
