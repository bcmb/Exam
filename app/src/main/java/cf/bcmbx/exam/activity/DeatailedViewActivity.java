package cf.bcmbx.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cf.bcmbx.exam.R;
import cf.bcmbx.exam.adapter.PersonAdapter;
import cf.bcmbx.exam.db.PersonManager;
import cf.bcmbx.exam.model.Person;

public class DeatailedViewActivity extends AppCompatActivity {
    private PersonManager pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_activity);
        Intent intent = getIntent();
        String id = intent.getStringExtra(PersonAdapter.PERSON_ID);
        pm = new PersonManager(getApplicationContext());
        List<Person> persons = pm.getUser(id);

        ImageView userImage = (ImageView) findViewById(R.id.detailed_contact_image);
        EditText firstName = (EditText) findViewById(R.id.detailed_fname);
        EditText lastName = (EditText) findViewById(R.id.detailed_lname);
        EditText gender = (EditText) findViewById(R.id.detailed_gender);
        EditText username = (EditText) findViewById(R.id.detailed_username);

        Picasso.with(this).load(persons.get(0).getImageUrl()).into(userImage);
        firstName.setText(persons.get(0).getFirstName());
        lastName.setText(persons.get(0).getLastName());
        gender.setText(persons.get(0).getGender());
        username.setText(persons.get(0).getUsername());
    }
}
