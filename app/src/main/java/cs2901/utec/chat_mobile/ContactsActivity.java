package cs2901.utec.chat_mobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView contactsActivityTitle = (TextView)findViewById(R.id.contactsActivityTitle);
        String username = getIntent().getStringExtra("username");
        String title = username + "'s contacts";
        contactsActivityTitle.setText(title);
    }

}
