package network.virus.behero_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;

/**
 * Created by PJunhyukMainDT on 2018-02-01.
 */

public class OpenapiActivity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_openapi);

        Intent intent = new Intent(this.getIntent());

        webView = (WebView) findViewById(R.id.openapi_webview);

        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);

        // Add a WebViewClient
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                // Inject CSS when page is done loading
                injectCSS();
                super.onPageFinished(view, url);
            }
        });

        // Load a webpage
        webView.loadUrl("http://www.safe182.go.kr/api/lcm/findChildListT.do?esntlId=10000190&authKey=f1fd6f8d88b34dcd&rowSize=10");
    }

    // Inject CSS method: read style.css from assets folder
// Append stylesheet to document head
    private void injectCSS() {
        try {
            InputStream inputStream = getAssets().open("style.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
