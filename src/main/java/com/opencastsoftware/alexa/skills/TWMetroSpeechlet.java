package com.opencastsoftware.alexa.skills;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TWMetroSpeechlet implements Speechlet {

    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        //no setup needed
    }

    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return getMetroUpdate();
    }

    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        if (intent.getName().equals(intentName)) {
            return getMetroUpdate();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse getMetroUpdate() throws SpeechletException {
        Document document  = null;
        try {
            document = Jsoup.connect("http://www.nexus.org.uk/metro/updates").get();
        } catch (IOException e) {
            throw new SpeechletException("Site Unavailable");
        }
        TWMetroUpdatePageParser updates = new TWMetroUpdatePageParser(document);
        String speechText = updates.toString();
        SimpleCard card = new SimpleCard();
        card.setTitle("MetroUpdates");
        card.setContent(speechText);
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
}
