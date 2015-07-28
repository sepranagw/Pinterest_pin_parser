package myrdiodemo.com.pinterest_pin_parser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class IndividualShow extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_individual_show);
        Intent intent = getIntent();
        String description = intent.getStringExtra(ResultActivityGrid.EXTRA_DESCRIPTION);
        String imageURL = intent.getStringExtra(ResultActivityGrid.EXTRA_URL);
        ImageView iv = (ImageView) findViewById(R.id.the_image);
        Picasso.with(getApplicationContext()).load(imageURL)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.camera_delete_icon)
                        //.resizeDimen(80, 80)
                        //.centerInside()
                .into(iv);
        TextView tv = (TextView)findViewById(R.id.the_description);
        tv.setText(description);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_individual_show, menu);
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
            Toast.makeText(getBaseContext(), "Version 1.0, English, Creator: Corinne Nakashima", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
