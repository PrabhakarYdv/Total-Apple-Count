package com.prabhakar.totalapplecount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TotalApplesFragment extends Fragment {
    private EditText enterAvailableApple;
    private TextView availableApple;
    private Button buttonNext;
    private CommunicationListener communicationListener;
    private int remainApple;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            remainApple =getArguments().getInt("remaining");
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
        return inflater.inflate(R.layout.fragment_total_apples, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        enterAvailableApple = view.findViewById(R.id.enter_available_apple);
        availableApple = view.findViewById(R.id.total_apple);
        buttonNext = view.findViewById(R.id.btn_next);
        availableApple.setText(remainApple+"");
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterAvailableApple.getText().toString().isEmpty()) {
                    availableApple.setText(enterAvailableApple.getText().toString());

                    int currentQuantity = Integer.parseInt(enterAvailableApple.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putInt("currentQuantity", currentQuantity);
                    communicationListener.LaunchBuyApplesFragment(bundle);
                } else {
                    Toast.makeText(getContext(), "Enter the available apple first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}