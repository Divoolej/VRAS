package com.ant.very.android;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.ant.very.ActionResolver;
import com.ant.very.objects.Ui;


/**
 * This class contains native android code that can be called by the core libGDX project.
 * Created by hubert on 09.11.14.
 */
public class ActionResolverAndroid implements ActionResolver {
    Handler uiThread;

    Context appContext;
    SpeechRecognizer speechRecognizer;

    private ConversationBot bot;
    private Ui ui;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
        try {
            bot = new ConversationBot();
        } catch (Exception e) {
            showToast("Error creating the chatbot: " + e, 5000);
        }
    }

    // Called by VRAS.
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void showToast(final CharSequence toastMessage, final int toastDuration) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, toastMessage, toastDuration).show();
            }
        });
    }

    @Override
    public void recognizeSpeech() {
        // Run from the main thread:
        Handler mainHandler = new Handler(appContext.getMainLooper());
        Runnable recognizeRunnable = new Runnable() {
            @Override
            public void run() {
                speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext);
                speechRecognizer.setRecognitionListener(new MyListener(appContext, ui));

                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                // The next line is needed to force english in 4.4 KitKat:
                i.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{});

                i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
    //                gdx.actionPulseMicButton();
                speechRecognizer.startListening(i);
            }
        };
        mainHandler.post(recognizeRunnable);
    }
}
