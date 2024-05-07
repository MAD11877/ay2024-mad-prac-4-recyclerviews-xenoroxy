package sg.edu.np.mad.madpractical4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        User user = new User("Rena Soong", "MAD Developer", 1, false);

        Toast toastFollow = Toast.makeText(this, "Followed", Toast.LENGTH_SHORT);
        Toast toastUnfollow = Toast.makeText(this, "Unfollowed", Toast.LENGTH_SHORT);



        int randomNumber = getIntent().getIntExtra("random_number", 0);

        TextView tvName = findViewById(R.id.tvname);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        tvName.setText(user.name + " " + String.valueOf(randomNumber));
        tvDescription.setText(user.description);
        btnFollow.setText("Follow");

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed) {
                    user.followed = false;
                    btnFollow.setText("Follow");
                    toastFollow.show();
                } else if (!user.followed) {
                    user.followed = true;
                    btnFollow.setText("Unfollow");
                    toastUnfollow.show();
                }
            }
        });
    }
}