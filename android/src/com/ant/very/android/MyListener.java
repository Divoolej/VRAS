package com.ant.very.android;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.ant.very.objects.Ui;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Custom listener used for receiving notifications from the
 * SpeechRecognizer when the recognition related events occur.
 */

public class MyListener implements RecognitionListener {
    public static final String TAG = "MyListener";

    private Context appContext;
    private TextToSpeech tts;
    private Ui ui;

    public MyListener(Context context, Ui ui) {
        appContext = context;
        this.ui = ui;
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
        Log.d(TAG, "Error # " + i);
//        ui.stopMicButtonPulse();
        switch (i) {
            case SpeechRecognizer.ERROR_AUDIO:
                ui.showToast("Audio error");
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                ui.showToast("Client error");
                break;
            case SpeechRecognizer.ERROR_SERVER:
                ui.showToast("Server error");
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                ui.showToast("There was a problem with your connection.");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                ui.showToast("Connection timed out");
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                ui.showToast("No matches found");
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                ui.showToast("I'm still listening");
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                ui.showToast("Insufficient permissions");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                ui.showToast("Try again");
                break;
            default:
                Gdx.app.log(TAG, "SpeechRecognizer Error #" + i);
        }

    }

    // Process the speech recognition results:
    @Override
    public void onResults(Bundle results) {
        Gdx.app.log(TAG, "onResults:" + results);
        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        // Write all the candidate sentences to console:
        Gdx.app.log(TAG, String.valueOf(data.size()) + " candidates.");
        for (String element : data) {
            Gdx.app.log(TAG, "result: " + element);
        }

        ui.setInputTextFieldText(data.get(0));

        try {
            handleResult(data.get(0));
        } catch (Exception e) {
            ui.showToast("Exception " + e);
        }
    }

    private void handleResult(String sentence) {
//        String response = bot.ask(sentence);
//        ui.setBotResponseTextAreaText(" " + response);
//        speakOutLoud(response);
    }

    private void speakOutLoud(String sentence) {
        if(sentence == null || "".equals(sentence)) { return; } // Ignore empty output.
        else tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
