package com.example.moodupteamtesttask;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;


public class FirstFragment extends Fragment {
    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    TextView txtRecipe, txtFacebook;
    LoginButton fb;
    View layout1, layout2;
    ImageView imgShadow;
    boolean isFABOpen;
    CallbackManager callbackManager;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        Application appCtx = (getActivity().getApplication());
        FacebookSdk.sdkInitialize(appCtx);
        AppEventsLogger.activateApp(appCtx);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab = view.findViewById(R.id.fab);
        fab1 = view.findViewById(R.id.fab1);
        fab2 = view.findViewById(R.id.fab2);

        fb =view.findViewById(R.id.login_button);

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
                closeFABMenu();
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //facebook login
                fbCallback();
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
     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         callbackManager.onActivityResult(requestCode, resultCode, data);
     }
    void fbCallback(){
        LoginManager loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginManager.logInWithReadPermissions(this, Arrays.asList("email"));
        loginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback < LoginResult > () {
            @Override
                public void onSuccess(LoginResult loginResult) {
                    Toast.makeText(getActivity(), "Login succesful!",
                            Toast.LENGTH_LONG).show();
                }
                    @Override
                    public void onCancel() {
                Toast.makeText(getActivity(),"Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getActivity(), "Login error!",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
