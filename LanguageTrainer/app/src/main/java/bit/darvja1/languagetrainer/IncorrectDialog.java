package bit.darvja1.languagetrainer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class IncorrectDialog extends DialogFragment {
    public IncorrectDialog(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Incorrect!");
        builder.setIcon(R.drawable.ic_incorrect);
        builder.setMessage("Tap anywhere outside of this box to dismiss.");

        Dialog customDialog = builder.create();

        return customDialog;
    }
}
