package com.example.activity.chapter18_handlingrotation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static final int PICK_REQUEST = 1337;
    Button viewButton = null;
    Uri contact = null;
    Button pickButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewButton = (Button) findViewById(R.id.buttonView);
        pickButton = (Button) findViewById(R.id.buttonPick);
        restoreMe(savedInstanceState);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (contact != null) {
            outState.putString("contact", contact.toString());
        }
    }

    private void restoreMe(Bundle state){
        contact = null;
        if (state != null) {
            String contactUri = state.getString("contact");
            if (contactUri != null) {
                contact = Uri.parse(contactUri);
            }
        }
    }
}
