package com.example.moodupteamtesttask;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstFragment extends Fragment {
    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    TextView txtRecipe, txtFacebook;
    View layout1, layout2;
    ImageView imgShadow;
    boolean isFABOpen;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab);
        fab1 = view.findViewById(R.id.fab1);
        fab2 = view.findViewById(R.id.fab2);
        imgShadow = view.findViewById(R.id.imgShadow);
        txtFacebook= view.findViewById(R.id.txtFacebook);
        txtRecipe= view.findViewById(R.id.txtRecipe);

        imgShadow.setVisibility(View.INVISIBLE);
        txtFacebook.setVisibility(View.INVISIBLE);
        txtRecipe.setVisibility(View.INVISIBLE);

        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    private void showFABMenu(){
        isFABOpen=true;
        fab.setRotation(45);
        imgShadow.setVisibility(View.VISIBLE);
        layout2.animate().translationY(-getResources().getDimension(R.dimen.standard_60));
        layout1.animate().translationY(-getResources().getDimension(R.dimen.standard_120));
        txtFacebook.setVisibility(View.VISIBLE);
        txtRecipe.setVisibility(View.VISIBLE);
    }
    private void closeFABMenu(){
        isFABOpen=false;
        fab.setRotation(90);
        imgShadow.setVisibility(View.INVISIBLE);
        layout1.animate().translationY(0);
        layout2.animate().translationY(0);
        txtFacebook.setVisibility(View.INVISIBLE);
        txtRecipe.setVisibility(View.INVISIBLE);
    }
}
