package network.virus.behero_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by PJunhyukMainDT on 2018-01-28.
 */

public class ThanksActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thanks);

        Intent intent = new Intent(this.getIntent());
    }

    public void onClick_goto_myletters(View view) {
        Intent intent = new Intent(this, MylettersActivity.class);
        startActivity(intent);
    }

    public void onClick_goto_home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick_goto_thankswrite(View view) {
        Intent intent = new Intent(this, ThankswriteActivity.class);
        startActivity(intent);
    }

}
