package com.ant.very.objects;

import com.ant.very.ActionResolver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class Ui {
    public static final String TAG = "Ui";
    private Stage stage;
    private Skin skin;

    private TextField inputTextField;
    private TextArea botResponseTextArea;
    private MicButton micButton;
    private Action shakeAction;
    private ActionResolver actionResolver;

    public Ui(ActionResolver ar) {
        actionResolver = ar;

        // Create the stage
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("Scene2D/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Create the actors
        botResponseTextArea = new TextArea("\n Tell me something!...", skin);
        inputTextField = new TextField("   ...", skin);
        micButton = new MicButton();
        setupActors();

        stage.addActor(inputTextField);
        stage.addActor(botResponseTextArea);
        stage.addActor(micButton);

        actionResolver.showToast("Created the UI.", 5000);
    }

    public void actionPulseMicButton() {
       shakeAction = Actions.forever(
           Actions.sequence(
               Actions.scaleTo(1.1f, 1.1f, 0.2f),
               Actions.scaleTo(1.0f, 1.0f, 0.2f),
               Actions.scaleTo(1.1f, 1.1f, 0.2f),
               Actions.scaleTo(1.0f, 1.0f, 0.2f),
               Actions.delay(0.6f)
           ));
        if (micButton.getActions().size == 0) {
            micButton.addAction(shakeAction);
            micButton.act(Gdx.graphics.getDeltaTime());
        }
    }

    public void setupActors() {
        float textBoxWidth = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 10.0f);
        float textBoxHeight = 1000;
        float offset = Gdx.graphics.getWidth() - textBoxWidth;

        botResponseTextArea.setWidth(textBoxWidth);
        botResponseTextArea.setHeight(textBoxHeight);
        botResponseTextArea.setPrefRows(2);
        botResponseTextArea.setX(offset - offset / 2.0f);
        botResponseTextArea.setY(Gdx.graphics.getHeight() - textBoxHeight);
        botResponseTextArea.setDisabled(true);

        inputTextField.setWidth(textBoxWidth);
        inputTextField.setHeight(textBoxHeight);
        inputTextField.setX(50);
        inputTextField.setY(50);

        micButton.setTouchable(Touchable.enabled);
        micButton.setPosition(inputTextField.getX() + inputTextField.getWidth() - micButton.getWidth(),
                inputTextField.getY() - micButton.getHeight());
    }

    public void stopMicButtonPulse() {
        micButton.removeAction(shakeAction);
        micButton.setScale(1.0f, 1.0f);
    }

    public void disposeUi() {
        stage.dispose();
        skin.dispose();
    }

    public void renderUI() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private class MicButton extends Actor {
        Texture texture = new Texture(Gdx.files.internal("Scene2D/mic.jpg"));

        protected MicButton() {
            setBounds(getX(), getY(), texture.getWidth()/2.0f, texture.getHeight()/2.0f);
            setOrigin(texture.getWidth()/4.0f, texture.getHeight()/4.0f);

            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                    actionResolver.recognizeSpeech();
                    return false;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                    getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0,
                    texture.getWidth(), texture.getHeight(), false, false);
        }
    }
}
