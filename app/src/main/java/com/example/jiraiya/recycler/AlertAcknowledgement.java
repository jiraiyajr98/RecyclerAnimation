package com.example.jiraiya.recycler;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AlertAcknowledgement extends AppCompatDialogFragment{

    OnOkClicked onOkClicked;
    AlertDialog alertDialog;
    private FragmentManager manager;
    private String title = "Acknowledgement";
    private boolean outsideClickcancelable = true;
    private String buttonText= "OK";
    private TextView textView;
    private String message = "Empty";
    private Button okButton;
    private String colorHEX = "#8dc63f";
    private Context context;
    View vh;


    @SuppressLint("ValidFragment")
    public AlertAcknowledgement (Context context){
        this.context = context;
        this.manager =((FragmentActivity)context).getSupportFragmentManager();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        vh = getActivity().getLayoutInflater().inflate(R.layout.dilague_acknowledgement,null);
        textView = (TextView)vh.findViewById(R.id.message_acknow);
        okButton = (Button)vh.findViewById(R.id.ok_acknow);

        textView.setText(message);
        okButton.setText(buttonText);
        okButton.setBackgroundColor(Color.parseColor(colorHEX));

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onOkClicked != null)
                onOkClicked.status(true);
            }
        });

        alertDialog = new AlertDialog.Builder(getActivity()).setView(vh).setTitle(title).create();
        alertDialog.setCanceledOnTouchOutside(outsideClickcancelable);
        return alertDialog;
    }

    void setOnOkClickedListener(OnOkClicked onOkClicked){
        this.onOkClicked =onOkClicked;
    }

    @Override
    public void onAttach(Context contextt) {
        super.onAttach(context);
        try{
            onOkClicked = (OnOkClicked)context;
        }catch (ClassCastException c){
            c.printStackTrace();
        }
    }

    void show(){
        show(manager,"Acknowledge");
    }

    void setMessage(String message){
        this.message=message;
    }

    void setButtonText(String buttonText){
        this.buttonText=buttonText;
    }

    void setButtonColor(String colorHEX){
        this.colorHEX=colorHEX;
    }
    void setTitle(String title){
        this.title = title;
    }
    void setCanceledOnTouchOutside(boolean outsideClickcancelable){
        this.outsideClickcancelable=outsideClickcancelable;
    }

    void tryy(){
        AnimationAlert.fade(getDialog().getWindow().getDecorView());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public interface OnOkClicked{
        void status(boolean status);
    }
}