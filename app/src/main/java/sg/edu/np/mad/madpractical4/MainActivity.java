package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get Components from Layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);

        // Get Data from Intent
        Intent receivingIntent = getIntent();
        String name = receivingIntent.getStringExtra("name");
        String description = receivingIntent.getStringExtra("description");
        boolean followed = receivingIntent.getBooleanExtra("followed", false);
        int id = receivingIntent.getIntExtra("id", 0);

        // Set Components with Values
        User user = new User(name, description, id, followed);
        tvName.setText(user.name);
        tvDescription.setText(user.description);
        btnFollow.setText("Follow");

        // OnClickListener for Follow Button
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed) {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                    user.followed = false;
                } else {
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollow", Toast.LENGTH_LONG).show();
                    user.followed = true;
                }
            }
        });

        // OnClickListener for Message Button
//        btnMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent displayMessageGroup = new Intent(MainActivity.this, MessageGroup.class);
//                startActivity(displayMessageGroup);
//            }
//        });
    }
}