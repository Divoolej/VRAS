package com.ant.very.android;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.tts.TextToSpeech;

import com.badlogic.gdx.Gdx;

import java.util.Locale;

/**
 * Custom listener used for receiving notifications from the
 * SpeechRecognizer when the recognition related events occur.
 */

public class MyListener implements RecognitionListener {
    public static final String TAG = "MyListener";

    Context appContext;
    TextToSpeech tts;

    public MyListener(Context context) {
        appContext = context;
        tts = new TextToSpeech(appContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = tts.setLanguage(Locale.UK);
                    if (lang == TextToSpeech.LANG_MISSING_DATA ||
                            lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Gdx.app.error(TAG, "Language not supported or missing");
                    }
                } else
                    Gdx.app.error(TAG, "Error initializing speech to text engine.");
            }
        });
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        // Stop mic button pulse and vibrate
    }

    @Override
    public void onError(int i) {
        // Stop mic button pulse and show error toast
    }

    @Override
    public void onResults(Bundle bundle) {

    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
