package cs2901.utec.chat_mobile;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;


import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView contactsActivityTitle = (TextView)findViewById(R.id.contactsActivityTitle);
        String username = getIntent().getStringExtra("username");
        String title = username + "'s contacts";
        contactsActivityTitle.setText(title);

        ArrayList<String> contacts = getIntent().getStringArrayListExtra("contacts");

        View contactsLayout = findViewById(R.id.contacts);

        for (int i = 0; i < contacts.size(); i++) {
            final TextView contact = new TextView(this);
            contact.setText(contacts.get(i));
            contact.setTextSize(30);
            contact.setGravity(Gravity.CENTER);
            contact.setClickable(true);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView contactClicked = (TextView) view;
                    CharSequence content = contact.getText();
                    showMessage("Clicked on " + content);
                }
            });
            contact.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ((LinearLayout) contactsLayout).addView(contact);
        }
    }

}
