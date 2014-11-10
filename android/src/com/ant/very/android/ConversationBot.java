package com.ant.very.android;

import android.os.AsyncTask;

import com.badlogic.gdx.Gdx;
import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

/**
 * An implementation of the ChatterBot API by pierredavidbelanger.
 * Created by hubert on 09.11.14.
 */

public class ConversationBot {
    ChatterBotFactory factory;
    ChatterBot bot;
    static ChatterBotSession botSession;

    public ConversationBot() throws Exception {
        factory = new ChatterBotFactory();
        bot = factory.create(ChatterBotType.JABBERWACKY); // I found it a bit faster than Cleverbot.
        botSession = bot.createSession();
    }

    public String ask(String what) throws Exception {
        RespondTask respondTask = new RespondTask();
        respondTask.execute(what);
        String response = respondTask.get();
        respondTask.cancel(true); // Kill the AsyncTask.
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
                Gdx.app.log("There was a problem communicating with the bot: ", e.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
