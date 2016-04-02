package bit.darvja1.languagetrainer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class CorrectDialog extends DialogFragment {
    public CorrectDialog(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Correct!");
        builder.setIcon(R.drawable.ic_correct);
        builder.setMessage("Tap anywhere outside of this box to dismiss.");


        Dialog customDialog = builder.create();

        return customDialog;
    }
}
