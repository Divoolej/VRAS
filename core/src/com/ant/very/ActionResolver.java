package com.ant.very;

import com.ant.very.objects.Ui;
import com.ant.very.utils.InputParser;

/**
 * A class that enables communication between core & android.
 */

public interface ActionResolver {
    public void setComponents(Ui ui, InputParser parser);
    public void showToast(CharSequence toastMessage, int toastDuration);
    public void recognizeSpeech();
    public void stopListeningForSpeech();
    public void handleBotQuestion(String sentence);

    public void moveAntUp();

    public void pickUpObject();

    public void buyItem();
}
