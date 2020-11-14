package com.pyropy.work24.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pyropy.work24.R;
import com.pyropy.work24.views.activities.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GettingStarted3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_getting_started_3, container,false);

        Button getStarted = (Button) root.findViewById(R.id.getstrt);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                try {
                    getActivity().finish();
                }catch (NullPointerException npe){
                    Log.d("GETTING_STARTED", "A null pointer error occured while shutting Splash activity");
                }
            }
        });

        TextView skipBtn = (TextView) root.findViewById(R.id.skip);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                try {
                    getActivity().finish();
                }catch (NullPointerException npe){
                    Log.d("GETTING_STARTED", "A null pointer error occured while shutting Splash activity");
                }
            }
        });

        ImageView imageView = (ImageView) root.findViewById(R.id.startedthree);
        ImageView indicator = (ImageView) root.findViewById(R.id.indicatorthree);
        //Glide.with(this).load(R.drawable.slide_five).into(imageView);
        //Glide.with(this).load(R.drawable.indicator_two).into(indicator);
        return root;
    }
}
