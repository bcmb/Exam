package cf.bcmbx.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import cf.bcmbx.exam.R;
import cf.bcmbx.exam.adapter.PersonAdapter;
import cf.bcmbx.exam.db.PersonManager;

public class UsersListActivity extends AppCompatActivity {
    private List mPersonsList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity_);
        mRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mPersonsList = new PersonManager(getApplicationContext()).getAllPersons();
        PersonAdapter mAdapter = new PersonAdapter(mPersonsList, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, AddUserActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(
                new PersonAdapter(
                        new PersonManager(getApplicationContext()).getAllPersons(), this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
