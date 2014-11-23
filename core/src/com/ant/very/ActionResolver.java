package com.ant.very;

import com.ant.very.objects.Ui;

/**
 * A class that enables communication between core & android.
 */

public interface ActionResolver {
    public void setUi(Ui ui);
    public void showToast(CharSequence toastMessage, int toastDuration);
    public void recognizeSpeech();
    public void stopListeningForSpeech();
}
