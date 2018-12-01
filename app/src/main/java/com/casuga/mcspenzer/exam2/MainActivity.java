package com.casuga.mcspenzer.exam2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText main_firstNameEditText, main_lastNameEditText, main_exam1EditText, main_exam2EditText;
    Button main_displayAveButton;

    TextView main_averageTextView;

    // Firebase Dependencies
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    // StudentRecord variables
    StudentRecord mStudentRecordFromDB;
    String latestKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_firstNameEditText = findViewById(R.id.main_firstNameEditText);
        main_lastNameEditText = findViewById(R.id.main_lastNameEditText);
        main_exam1EditText = findViewById(R.id.main_exam1EditText);
        main_exam2EditText = findViewById(R.id.main_exam2EditText);

        main_averageTextView = findViewById(R.id.main_averageTextView);

        main_displayAveButton = findViewById(R.id.main_displayAveButton);
        // TODO: Set onclick listener
        main_displayAveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = main_firstNameEditText.getText().toString();
                String lastName = main_lastNameEditText.getText().toString();
                int exam1 = Integer.parseInt(main_exam1EditText.getText().toString());
                int exam2 = Integer.parseInt(main_exam2EditText.getText().toString());

                // Firebase Initializations
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                mDatabaseReference = mFirebaseDatabase.getReference("grade");

                StudentRecord studentRecord = new StudentRecord(firstName, lastName, (exam1 + exam2) / 2);

                addNewRecord(studentRecord);
            }
        });


    }

    private void addNewRecord(StudentRecord studentRecord) {
        String key = mDatabaseReference.push().getKey();
        latestKey = key;
        mDatabaseReference.child(key).setValue(studentRecord);

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    mStudentRecordFromDB = studentSnapshot.getValue(StudentRecord.class);
                }

                main_averageTextView.setText("Your average is: " + mStudentRecordFromDB.getStudent_average());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
