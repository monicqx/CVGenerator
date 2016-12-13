package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.monic.cvgenerator.Classes.CVTipsWorker;

import org.jsoup.Jsoup;
import org.w3c.dom.Text;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        CVTipsWorker worker=new CVTipsWorker(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                TextView textTV=(TextView)findViewById(R.id.T_textTV);
                textTV.setText(s);
            }
        };
        worker.execute("https://www.theguardian.com/culture-professionals-network/culture-professionals-blog/2012/mar/15/cv-tips-first-arts-job");

    }
}
