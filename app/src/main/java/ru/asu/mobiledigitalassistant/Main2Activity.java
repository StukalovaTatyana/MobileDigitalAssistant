package ru.asu.mobiledigitalassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

/*    private void Postget(){
        URL url = new URL("http://developer.alexanderklimov.ru/android/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
    finally {
                urlConnection.disconnect();
            }
        }
    }
    */
}
