package sg.edu.np.mad.madpractical4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView= findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int random = new Random().nextInt(999999);
            boolean randomBool = new Random().nextBoolean();
            String profileName = "Name " + random;
            String description = "Description " + random;
            Boolean followed = randomBool;
            int id = i + 2;
            User user = new User(profileName, description, id, followed);
            userList.add(user);
        }
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile")
                .setMessage("MADnesss")
                .setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        launchMainActivity();
                    }
                })
                .setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void launchMainActivity() {
        int randomInt = (int) (Math.random() * 100000);

        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("random_number", "1234");
        //intent.putExtra("random_number", String.valueOf(randomInt));
        intent.putExtra("random_number", randomInt);
        startActivity(intent);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public UserViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(android.R.id.text1);
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
        ArrayList<String> data;

        public class UserAdapter(ArrayList<String> input) {
            data = input;
        }
         @NonNull
         public UserViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
             View item = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.custom_activity_list, parent, false);
             return new UserViewHolder(item);
         }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        }

        public void OnBindViewHolder (UserViewHolder holder, int position) {
            String s = data.get(position);
            holder.txt.setText(s);
         }

         public int getItemCount() {
            return data.size();
         }
    }
}