package com.ant.very.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ant.very.VRAS;

public class AndroidLauncher extends AndroidApplication {

    ActionResolverAndroid actionResolver;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        VRAS vras = new VRAS(actionResolver);
        initialize(vras, config);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // These two services need to be stopped, otherwise they grow up to become Hitler.
        actionResolver.destroySpeechRecognizer();
        actionResolver.shutDownTtsEngine();
    }
}
