package network.virus.behero_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by PJunhyukMainDT on 2018-01-28.
 */

public class ThankswriteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thankswrite);

        Intent intent = new Intent(this.getIntent());
    }

    public void onClick_thankswrite_exit(View view) {
        Snackbar.make(view, "Exit?!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
