package com.opencastsoftware.alexa.skills;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class TWMetroSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
    }

    public TWMetroSpeechletRequestStreamHandler() {
        super(new TWMetroSpeechlet(), supportedApplicationIds);
    }
}
