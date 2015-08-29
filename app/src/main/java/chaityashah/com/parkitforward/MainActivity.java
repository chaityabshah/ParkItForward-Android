package chaityashah.com.parkitforward;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//parkitforward.azurewebsites.net/users/register
    public void onButtonClick(View v) throws IOException {
        Button button = (Button) v;
        EditText vinObj = (EditText) findViewById(R.id.vinText);
        String vin = vinObj.toString();

        // post request to send VIN and android id
        //apiQuery();


        Intent intent = new Intent(this, ParkActivity.class);
        startActivity(intent);

    }

    public String getToken() throws IOException {
        URL url = new URL("https://developer.gm.com/api/v1/oauth/access_token?grant_type=client_credentials");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Basic bDd4eGIxYWNlM2I0YzQ0NTRmMWE5ZWM3YTMwYjY5Yzg1MGY2OjVkNDEwMzVhNTYxOTQxM2Q4Y2ViZTZlYjFjNGJkOWVi");
        return null;
    }

    public void apiQuery() throws IOException {
        String token = getToken();

        StringBuilder urlBuilder = new StringBuilder("https://papi-plpl7g-sb.onstar.gm.com:20701/api/v1/account/vehicles/{vin}".replace("{vin}", URLEncoder.encode("1G1RD6E44CU000002", "UTF-8")));
        urlBuilder.append("?");
        urlBuilder.append(URLEncoder.encode("includeCommands","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8") + "&");
        urlBuilder.append(URLEncoder.encode("includeFeatures","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8") + "&");
        urlBuilder.append(URLEncoder.encode("includeProgram","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8") + "&");
        urlBuilder.append(URLEncoder.encode("includeCarrierAccount","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/xml");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

}
