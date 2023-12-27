package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;
public class InstructionActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    TextView instructionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        instructionId = findViewById(R.id.instText);
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
                    }
                }
            }
        });
    }

    private void openCamera() {
//        try{
//            TimeUnit.SECONDS.sleep(4);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        }catch(InterruptedException e){
//            //
//        }
    }

    private void speak() {
        String text = instructionId.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }
    public void onClick(View view)
    {
        openCamera();
    }

}
