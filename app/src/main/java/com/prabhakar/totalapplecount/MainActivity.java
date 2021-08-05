package com.prabhakar.totalapplecount;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements CommunicationListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TotalApplesFragment totalApplesFragment = new TotalApplesFragment();
        fragmentTransaction.add(R.id.fragment, totalApplesFragment, "TotalAppleFragment").commit();
    }


    @Override
    public void LaunchTotalApplesFragment(Bundle bundle) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TotalApplesFragment totalApplesFragment = new TotalApplesFragment();
        totalApplesFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, totalApplesFragment, "Total Fragment").addToBackStack("final total apple").commit();
    }

    @Override
    public void LaunchBuyApplesFragment(Bundle bundle) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuyApplesFragment buyApplesFragment = new BuyApplesFragment();
        buyApplesFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, buyApplesFragment, "BuyApple").addToBackStack("apple buy").commit();

    }
}