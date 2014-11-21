package com.ant.very.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import com.ant.very.objects.Ui;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * Custom listener used for receiving notifications from the
 * SpeechRecognizer when the recognition related events occur.
 */

public class MyListener implements RecognitionListener {
    public static final String TAG = "MyListener";

    private Context appContext;
    private Ui ui;
    private ConversationBot bot;

    public MyListener(Context context, Ui ui, ConversationBot bot) {
        appContext = context;
        this.bot = bot;
        this.ui = ui;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float volumeDB) {
//        This gets called when the input voice volume changes. May be useful.
//        float volumeNo = (volumeDB+120)/1.8f; // Normalize to 0-100 scale.
    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        // TODO Call this when you touch the mic button a second time too.
        // Stop the mic button pulse and vibrate just a bit.
        Gdx.app.log(TAG, "onEndOfSpeech");
        ui.stopMicButtonPulse();
        Vibrator v = (Vibrator)appContext.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
    }

    @Override
    public void onError(int i) {
        // Stop mic button pulse and show error toast
        ui.stopMicButtonPulse();
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

    private void handleResult(String sentence) throws Exception {
        String response = bot.ask(sentence);
        ui.setBotResponseTextAreaText("\n " + response);
        speakOutLoud(response);
    }

    private void speakOutLoud(String sentence) {
        // Ignore empty input.
        if(!( sentence == null || "".equals(sentence)) )
            bot.getTts().speak(sentence, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
