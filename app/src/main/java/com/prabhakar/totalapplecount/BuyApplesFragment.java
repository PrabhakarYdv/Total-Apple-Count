package com.prabhakar.totalapplecount;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BuyApplesFragment extends Fragment {
    private EditText enterAppleQuantityToBuy;
    private CommunicationListener communicationListener;
    private int currentAvailableApple;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentAvailableApple = getArguments().getInt("currentQuantity");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        communicationListener = (CommunicationListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_apples, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enterAppleQuantityToBuy = view.findViewById(R.id.enter_apple_quantity_to_buy);

    }

    @Override
    public void onPause() {
        super.onPause();
        buyAppleProcess();
    }

    private void buyAppleProcess() {
        int buyApple = 0;
        if (!enterAppleQuantityToBuy.getText().toString().isEmpty()) {
            buyApple = Integer.parseInt(enterAppleQuantityToBuy.getText().toString());
        }
        Bundle bundle = new Bundle();
        if (buyApple <= currentAvailableApple) {
            int remainApple = currentAvailableApple - buyApple;
            bundle.putInt("remaining", remainApple);
            communicationListener.LaunchTotalApplesFragment(bundle);
        } else if (currentAvailableApple==0){
            new AlertDialog.Builder(getContext()).setTitle("Out of Stock !!")
                    .setMessage("Sorry , for now apple is out of stock please try after sometime")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        else{
            bundle.putInt("remaining", currentAvailableApple);
            communicationListener.LaunchTotalApplesFragment(bundle);
            new AlertDialog.Builder(getContext()).setTitle("Out Of Stock")
                    .setMessage("Sorry ! \n" + buyApple + " apple is not in our stock , for now you can purchase " + currentAvailableApple + " apple Max").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }

}