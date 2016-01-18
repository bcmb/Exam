package cf.bcmbx.exam.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cf.bcmbx.exam.R;
import cf.bcmbx.exam.db.PersonManager;
import cf.bcmbx.exam.model.Person;

public class FetchPersonAsyncTask extends AsyncTask<String, Void, Person> {
    private ProgressDialog pDialog;
    private Context mContext;
    private Person mResult;
    private PersonManager mManager;
    private List<View> mViews;

    public FetchPersonAsyncTask(Context c, PersonManager mManager, List mViews) {
        mContext = c;
        this.mManager = mManager;
        this.mViews = mViews;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage(mContext.getString(R.string.wait));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Person doInBackground(String... items) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json;
        Log.d("TAG", "inside do in background");
        final String PERSON_GENERATOR_URL = "https://randomuser.me/api/";

        try {
            URL url = new URL(PERSON_GENERATOR_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                json = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                json = null;
            }
            json = buffer.toString();
        } catch (IOException e) {
            json = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray resultsArray = jsonObj.getJSONArray("results");
            JSONObject personObj = resultsArray.getJSONObject(0);
            JSONObject usrObj = personObj.getJSONObject("user");
            String gender = usrObj.getString("gender");
            String username = usrObj.getString("username");
            JSONObject nameObj = usrObj.getJSONObject("name");
            String firstName = nameObj.getString("first");
            String lastName = nameObj.getString("last");
            JSONObject pictureObj = usrObj.getJSONObject("picture");
            String pictureUrl = pictureObj.getString("medium");
            mManager.insertUser(firstName, lastName, pictureUrl, gender, username);
            mResult = new Person(firstName, lastName, pictureUrl, gender, username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResult;
    }

    @Override
    protected void onPostExecute(Person result) {
        super.onPostExecute(result);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        Picasso.with(mContext).load(result.getImageUrl())
                .into((ImageView) mViews.get(0));
        ((EditText) mViews.get(1)).setText(result.getFirstName());
        ((EditText) mViews.get(2)).setText(result.getLastName());
        ((EditText) mViews.get(3)).setText(result.getGender());
        ((EditText) mViews.get(4)).setText(result.getUsername());
    }
}
