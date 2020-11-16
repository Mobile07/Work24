package com.pyropy.work24.database;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseUtil {

    public static FirebaseUtil mFirebaseUtil;
    public static FirebaseDatabase mFirebaseDatabase;
    public static FirebaseStorage mFirebaseStorage;
    public static FirebaseAuth mFirebaseAuth;

    private FirebaseUtil(){}

    public static FirebaseUtil getInstances(Context uContext){
        if (mFirebaseUtil == null){
            mFirebaseUtil = new FirebaseUtil();
        }
        instantiateFDb();
        instantiateFStore();
        instantiateFAuth();
        return mFirebaseUtil;
    }

    private static void instantiateFStore() {
        mFirebaseStorage = FirebaseStorage.getInstance();
    }

    private static void instantiateFDb() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    private static void instantiateFAuth(){
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

}
