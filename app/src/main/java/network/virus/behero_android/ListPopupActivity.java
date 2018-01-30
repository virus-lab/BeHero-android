package network.virus.behero_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import network.virus.behero_android.popupWindow.ReAlert;

public class ListPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_popup);

        Button report_btn = findViewById(R.id.report_btn);
        report_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent intent = new Intent(ListPopupActivity.this,ChangePW_Activity.class);
                startActivity(intent);
            }
        });

        Button reAlert_btn = findViewById(R.id.reAlert_btn);
        reAlert_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ListPopupActivity.this,ReAlert.class);
                startActivity(intent);
            }
        });
    }
}
