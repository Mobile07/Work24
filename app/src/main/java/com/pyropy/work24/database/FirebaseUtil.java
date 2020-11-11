package com.pyropy.work24.database;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtil {

    public static FirebaseUtil mFirebaseUtil;
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static FirebaseStorage mFirebaseStorage;
    public static StorageReference mStorageReference;

    private FirebaseUtil(){}

    public static void openFbReference(String ref, Context uContext){
        if (mFirebaseUtil == null){
            mFirebaseUtil = new FirebaseUtil();
        }
    }

}
