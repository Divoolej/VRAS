package com.ant.very.android;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;

import com.badlogic.gdx.Gdx;
import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import java.util.HashMap;
import java.util.Locale;

/**
 * An implementation of the ChatterBot API by pierredavidbelanger.
 * Created by hubert on 09.11.14.
 */

public class ConversationBot {
    private static final String TAG = "ConversationBot";
    Context appContext;

    ChatterBotFactory factory;
    ChatterBot bot;
    TextToSpeech tts;
    static ChatterBotSession botSession;

    private HashMap<String, String> historyMap;

    public ConversationBot(Context appContext) throws Exception {
        this.appContext = appContext;
        factory = new ChatterBotFactory();
        bot = factory.create(ChatterBotType.PANDORABOTS, "a41310638e34fe16"); // I found it a bit faster than Cleverbot.
        botSession = bot.createSession();

        historyMap = new HashMap<>();

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

    public TextToSpeech getTts() {
        return tts;
    }

    public String ask(String what) throws Exception {
        RespondTask respondTask = new RespondTask();
        respondTask.execute(what);
        String response = respondTask.get();
        respondTask.cancel(true); // Kill the AsyncTask.

        historyMap.put(what, response);

        Gdx.app.log("MAP", what + " | " + response);

        return response;
    }

    // A task that will run on a separate thread. It takes in and returns a String.
    private class RespondTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String response = new String();
            try {
                response = botSession.think(strings[0]);
            } catch (Exception e) {
                if (e.getMessage()==null)
                    e.printStackTrace();
                else {
                    Gdx.app.log("There was a problem connecting with the bot: ", e.getMessage());
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public HashMap<String, String> getHistoryMap() {
        return historyMap;
    }
}
