package com.example.gulpari.onbackups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//Firebase variables
    private FirebaseDatabase grantsDB;
    private DatabaseReference grantsReference;

    //Interface variables
    private EditText grandName;
    private EditText grandDescription;
    private Button publishButton;
    private TextView grandsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Firebase database and reference

        grantsDB = FirebaseDatabase.getInstance();
        grantsReference = grantsDB.getReference().child("grants");

        //Initialize interface

        grandName = (EditText)findViewById(R.id.grandDescription);
        grandDescription = (EditText)findViewById(R.id.grandDescription);
        publishButton = (Button)findViewById(R.id.publishButton);
        grandsText = (TextView)findViewById(R.id.grandsText);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Grant grant = new Grant (grandName.getText().toString(),
                        grandDescription.getText().toString ());
                grantsReference.push().setValue(grant);
            }
        });

        grantsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grant grant = dataSnapshot.getValue(Grant.class);
                grandsText.append(grant.getGrantName() + "\n");
                grandsText.append(grant.getGrantDescription() + "\n\n\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

             }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        grantsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grant grant = dataSnapshot.getValue(Grant.class);
                grandsText.append(grant.getGrantName()+ "\n");
                grandsText.append(grant.getGrantDescription() +"\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
           }
}
