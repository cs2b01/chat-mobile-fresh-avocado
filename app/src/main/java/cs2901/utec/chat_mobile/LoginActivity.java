package cs2901.utec.chat_mobile;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;
import org.json.JSONException;
import android.view.View;



public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // activity_login will be related to the content view
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public Activity getActivity() {
        return this;
    }

    public void onBtnLoginClicked(View view) {
        // 1. Getting username and password inputs from view
        EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        // 2. Creating a message from user input data
        Map<String, String> message = new HashMap<>();
        message.put("username", username);
        message.put("password", password);

        // 3. Converting the message object to JSON string (jsonify)
        JSONObject jsonMessage = new JSONObject(message);

        // 4. Sending json message to Server
        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.POST,
           "http://10.0.2.2:8080/authenticate",
            jsonMessage,
            new Response.Listener<JSONObject>() { // que voy a hacer cuando responda bien
                @Override
                public void onResponse(JSONObject response) {
                    // TODO
                    try {
                        String message = response.getString("message");
                        String username = response.getString("user"); // logged user username
                        JSONArray contacts = response.getJSONArray("contacts");
                        if (message.equals("Authorized")) {
                            showMessage("Authenticated");
                            Intent intent = new Intent(getActivity(), ContactsActivity.class);
                            intent.putExtra("username", username);
                            ArrayList<String> list = new ArrayList<>();
                            for (int i = 0; i < contacts.length(); i++) {
                                list.add(contacts.getString(i));
                            }
                            intent.putExtra("contacts", list);
                            startActivity(intent);
                        }
                        else {
                            showMessage("Wrong username or password");
                        }
                        showMessage(response.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        showMessage(e.getMessage());
                    }
                }
            },
            new Response.ErrorListener() { // que voy a hacer cuando responda mal
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    if( error instanceof  AuthFailureError ){
                        showMessage("Unauthorized");
                    }
                    else {
                        showMessage(error.getMessage());
                    }
                }
            }
        );
        /// sin este paso, el request no se envía:
        RequestQueue queue = Volley.newRequestQueue(this); // crea una cola de requests
        queue.add(request); // anade el request a la cola de requests
    }

}



















