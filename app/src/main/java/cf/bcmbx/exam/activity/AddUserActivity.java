package cf.bcmbx.exam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cf.bcmbx.exam.R;
import cf.bcmbx.exam.asynctask.FetchPersonAsyncTask;
import cf.bcmbx.exam.db.PersonManager;

public class AddUserActivity extends AppCompatActivity {
    private List<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_layout);
        ImageView userPicture = (ImageView) findViewById(R.id.add_contact_image);
        EditText firstName = (EditText) findViewById(R.id.add_fname);
        EditText lastName = (EditText) findViewById(R.id.add_lname);
        EditText gender = (EditText) findViewById(R.id.add_gender);
        EditText username = (EditText) findViewById(R.id.add_username);
        String stub = "";
        firstName.setText(stub);
        lastName.setText(stub);
        gender.setText(stub);
        username.setText(stub);
        views.add(userPicture);
        views.add(firstName);
        views.add(lastName);
        views.add(gender);
        views.add(username);
        new FetchPersonAsyncTask(this, new PersonManager(this), views).execute();
    }
}
