package com.example.shankarpentyala.course_buddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final TextView textView = (TextView)findViewById(R.id.chattextView);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("MSG");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value  = dataSnapshot.getValue().toString();
                textView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Chat.this,"Sorry limit reached",Toast.LENGTH_LONG).show();
            }
        });
        textView.setVisibility(View.VISIBLE);
    }
    public void onclick(View view)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("MSG");
        EditText e = (EditText)findViewById(R.id.chatText2);
        String x = e.getText().toString();
        EditText editText = (EditText)findViewById(R.id.chatText);
        String s = editText.getText().toString();
        TextView t = (TextView)findViewById(R.id.chattextView);
        String b = t.getText().toString();
        reference.setValue(b+"\n\n"+x+" : "+s);
        editText.setText("");
        e.setVisibility(View.GONE);
    }
}