package network.virus.behero_android.popupWindow;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import network.virus.behero_android.R;

/**
 * Created by LG on 2018-01-28.
 * Login alert Dialog
 */

public class SendAlert extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.send_alert)
                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // YEs
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}