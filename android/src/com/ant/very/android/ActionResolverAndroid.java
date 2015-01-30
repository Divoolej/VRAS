package com.ant.very.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.very.ActionResolver;
import com.ant.very.objects.Ant;
import com.ant.very.objects.Ui;
import com.ant.very.utils.Parser;
import com.badlogic.gdx.Gdx;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class contains native android code that can be called by the core libGDX project.
 * Created by hubert on 09.11.14.
 */

public class ActionResolverAndroid implements ActionResolver {
    Context appContext;
    Handler uiThread;

    private Ui ui;
    private SpeechRecognizer speechRecognizer;
    private MyListener listener;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
        try {
            ConversationBot.setContext(appContext);
        } catch (Exception e) {
            showToast("Error creating the chatbot: " + e, 5000);
        }
    }

    // Called by VRAS.
    public void setComponents(Ui ui, Parser parser) {
        this.ui = ui;
        listener = new MyListener(appContext, ui, parser);
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
                speechRecognizer.setRecognitionListener(listener);

                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                // The next line is needed to force english in 4.4 KitKat:
                i.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{});

                i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
                ui.actionPulseMicButton();
                speechRecognizer.startListening(i);
            }
        };
        mainHandler.post(recognizeRunnable);
    }

//    Called when you press the mic button a second time:
    public void stopListeningForSpeech() {
        Handler mainHandler = new Handler(appContext.getMainLooper());
        Runnable stopRecognizingRunnable = new Runnable() {
            @Override
            public void run() {
                Gdx.app.log("actionresolver", "stopping listening");
                ui.stopMicButtonPulse();
                speechRecognizer.stopListening();
            }
        };
        mainHandler.post(stopRecognizingRunnable);
    }

    @Override
    public void handleBotQuestion(String sentence) {
        try {
            listener.handleResult(sentence);
        } catch (Exception e) {
            showToast(e.getMessage(), 5000);
        }
    }

    @Override
    public void buyItem(String item) {

    }

    @Override
    public void moveAnt(String direction) {
        Ant.getInstance().moveInDirection(direction);
    }

    @Override
    public void setResponseFieldText(String s) {
        ui.setBotResponseTextAreaText(s);
    }

    public void shutDownTtsEngine() {
        ConversationBot.getInstance().getTts().shutdown();
    }

    public void destroySpeechRecognizer() {
        if(ui.isCurrentlyRecognizingSpeech()) {
            speechRecognizer.destroy();
        }
    }

    public void showHistoryDialog() {
        LinkedHashMap<String, String> historyMap =  ConversationBot.getInstance().getHistoryMap();
        TextView tv = new TextView(appContext);
        final AlertDialog.Builder builder = new AlertDialog.Builder(appContext);

        tv.setTextSize(20);
        tv.setVerticalScrollBarEnabled(true);
        tv.setMovementMethod(new ScrollingMovementMethod());
        Iterator it = historyMap.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            tv.setText(tv.getText() + "" + pairs.getKey() + "\nAnt: " + pairs.getValue() + "\n\n");
        }

        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Chat history:");
        builder.setView(tv);
        builder.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                builder.show();
            }
        });
    }
}