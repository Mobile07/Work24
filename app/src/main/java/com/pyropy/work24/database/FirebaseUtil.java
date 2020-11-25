package com.pyropy.work24.database;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtil {

    public static FirebaseUtil mFirebaseUtil;
    public static FirebaseDatabase mFirebaseDatabase;
    public static FirebaseStorage mFirebaseStorage;
    public static FirebaseAuth mFirebaseAuth;
    public static DatabaseReference mDbRef;
    public static StorageReference mStoreRef;
    public static String mAuthEmail="";

    private FirebaseUtil(){}

    public static FirebaseUtil getInstances(Context uContext){
        if (mFirebaseUtil == null){
            mFirebaseUtil = new FirebaseUtil();
        }
        instantiateFDb();
        instantiateFStore();
        return mFirebaseUtil;
    }

    private static void instantiateFStore() {
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStoreRef = mFirebaseStorage.getReference();
    }

    private static void instantiateFDb() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDbRef = mFirebaseDatabase.getReference();
    }

}
