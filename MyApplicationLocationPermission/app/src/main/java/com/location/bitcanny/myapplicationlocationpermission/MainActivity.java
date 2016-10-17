package com.location.bitcanny.myapplicationlocationpermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "PemissionActivity";

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    private final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

/*
        boolean mystate = requestPermission(this,Manifest.permission.ACCESS_FINE_LOCATION,"This app needs your location access",MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        if(mystate){
            Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
        }*/



    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d(TAG,"onBackPressed");
        moveTaskToBack(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("Persmisson",requestCode+"/"+permissions[0]+"/"+grantResults);
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                finish();
            }
        }


    }




    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener,
                                           DialogInterface.OnClickListener cancelListner) {
        new android.support.v7.app.AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListner)
                .create()
                .show();
    }

    public static boolean requestPermission(Activity context, final String permission, String rational, final int requestCode){
        if(ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                final String perm = permission;
                final Activity cntxt = context;
                final int rqstCod = requestCode;
                showMessageOKCancel(context,rational,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(cntxt,
                                        new String[]{perm},
                                        rqstCod);
                            }
                        },new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    cntxt.onRequestPermissionsResult(requestCode,new String[]{permission},new int[]{PackageManager.PERMISSION_DENIED});
                                }
                            }
                        });
                return false;

            } else {

                // No explanation needed, we can request the permission.
                // PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                ActivityCompat.requestPermissions(context,
                        new String[]{permission},
                        requestCode);
                return false;
            }

        }
        else{
            //usr has permission to read contact
            return true;
        }
    }


}
