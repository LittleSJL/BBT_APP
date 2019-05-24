package com.example.testtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView response_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        response_text = (TextView)findViewById(R.id.response_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });

        /*WebView webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://47.101.53.62:8080/Loginserviet");*/


    }

    private void showResponse(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                response_text.setText(response);
            }
        });
    }

    private void sendRequestWithOkHttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("account","admin");
                    builder.add("password","123456");

                    Request request = new Request.Builder()
                            .url("http://47.101.53.62:8080/Loginserviet")
                            .post(builder.build())
                            .build();

                    Response response = client.newCall(request).execute();
                    String responsedata = response.body().toString();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
