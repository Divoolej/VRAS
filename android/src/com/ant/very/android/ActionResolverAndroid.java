package com.ant.very.android;

import android.content.Context;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.ant.very.ActionResolver;


/**
 * This class contains native android code that can be called by the core libGDX project.
 * Created by hubert on 09.11.14.
 */
public class ActionResolverAndroid implements ActionResolver {
    Handler uiThread;

    Context appContext;
    SpeechRecognizer speechRecognizer;

    private ConversationBot bot;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
        try {
            bot = new ConversationBot();
        } catch (Exception e) {
            showToast("Error creating the chatbot: " + e, 5000);
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext);
        speechRecognizer.setRecognitionListener(new MyListener(appContext));
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
        uiThread.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
