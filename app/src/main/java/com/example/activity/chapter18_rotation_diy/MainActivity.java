package com.example.activity.chapter18_rotation_diy;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    static final int PICK_REQUEST = 1337;
    Button viewButton = null;
    Button pickButton = null;
    Uri contact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewButton = (Button) findViewById(R.id.buttonView);
        pickButton = (Button) findViewById(R.id.buttonPick);
        viewButton.setEnabled(contact != null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_REQUEST) {
            if (resultCode == RESULT_OK) {
                contact = data.getData();
                viewButton.setEnabled(true);
            }
        }
    }

    public void pickContact(View v){
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, PICK_REQUEST);
    }

    public void viewContact(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, contact));
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            container.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            container.setOrientation(LinearLayout.VERTICAL);
        }
    }
}
