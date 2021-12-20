package co.edu.usa.reto4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity implements Runnable{

    Thread h1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView logoApp = (ImageView) findViewById(R.id.logoApp);
        logoApp.setImageResource(R.drawable.chaquetaiconowhite);


        h1 = new Thread(this);
        h1.start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            Intent pasarPantalla = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(pasarPantalla);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}