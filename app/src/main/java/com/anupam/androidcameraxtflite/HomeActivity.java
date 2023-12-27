package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.speech.tts.TextToSpeech.QUEUE_ADD;

public class HomeActivity extends AppCompatActivity {

    LinearLayout l1;
    TextToSpeech textToSpeech;
    TextView welcomeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        welcomeId = findViewById(R.id.welcome);

        welcomeId.setVisibility(View.VISIBLE);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "This language is not supported!",
                                Toast.LENGTH_SHORT);
                    } else {
                        //btnSpeak.setEnabled(true);
                        textToSpeech.setPitch(0.6f);
                        textToSpeech.setSpeechRate(1.0f);

                            speak();

                        //textToSpeech.playSilentUtterance(2000,QUEUE_ADD, null);
                    }
                }
            }
        });

//        l1 = findViewById(R.id.welcomeScreen);
//        l1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//            openInstructions();

//

    }

    private void openInstructions() {
        try{
            TimeUnit.SECONDS.sleep(4);
            startActivity(new Intent(getApplicationContext(),InstructionActivity.class));
        }catch(InterruptedException e){
            //
        }
    }

    private void speak() {//throws InterruptedException {
        String text = welcomeId.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
//        TimeUnit.SECONDS.sleep(3);
//        for(int i = 0;i <= 5000;i++){
//
//        }
        openInstructions();
    }


}