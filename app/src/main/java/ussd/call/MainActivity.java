package ussd.call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edInputUssdCode;
    Button btnDialUssdCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        edInputUssdCode = (EditText) findViewById(R.id.ed_inputUssd);
        btnDialUssdCode = (Button) findViewById(R.id.btnDialUssdCode);

        btnDialUssdCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UssdCode = edInputUssdCode.getText().toString();

                //check if edittext is empty
                if (UssdCode.equalsIgnoreCase("")) {

                    Toast.makeText(MainActivity.this, "Please Input ussd code", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check if its a valid ussd code
                if (UssdCode.startsWith("*") && UssdCode.endsWith("#")) {

                    //we want to remove the last # from the ussd code as we need to encode it. so *555# becomes *555
                    UssdCode = UssdCode.substring(0, UssdCode.length() - 1);

                    String UssdCodeNew = UssdCode + Uri.encode("#");

                    //request for permission
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                    } else {
                        //dial Ussd code
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + UssdCodeNew)));

                    }


                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid ussd code", Toast.LENGTH_SHORT).show();
                }


            }
        });


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
}
