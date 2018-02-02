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

public class CallAlert extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage(R.string.call_Alert)
                .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return dialog.show();
    }
}