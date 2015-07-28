package myrdiodemo.com.pinterest_pin_parser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class ResultActivityGrid extends ActionBarActivity {

    GridView gridView;
    public final static String EXTRA_DESCRIPTION = "default1";
    public final static String EXTRA_URL = "default2";
    //static final String[] MOBILE_OS = new String[] { "Greece", "Germany","Italy", "Britain" };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity_grid);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final ArrayList<String> message = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE1);
        final ArrayList<String> message2 = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE2);

        gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(new MyAdapter(this, message2));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v,
                                    int position, long id) {
                /*Toast.makeText(
                        getApplicationContext(),
                        (message.get(position)), Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(getApplicationContext(), IndividualShow.class);
                //String result1 = result.description.get(1);//start with returning string description of 1st index
                intent.putExtra(EXTRA_DESCRIPTION, message.get(position));
                intent.putExtra(EXTRA_URL, message2.get(position));
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result_activity_grid, menu);
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
