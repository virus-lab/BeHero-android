package network.virus.behero_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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
}
