package myrdiodemo.com.pinterest_pin_parser;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static android.widget.TableRow.LayoutParams;


public class ResultActivity extends ActionBarActivity {
//sample stuff begin:
    TableLayout table_layout;
    public final static String EXTRA_MESSAGE3 = "message";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();
		ArrayList<String> message = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE1);
        ArrayList<String> message2 = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE2);

        if(message.get(0)=="Invalid"){
            onURLError(message2.get(0));
        }
        else {
            table_layout = (TableLayout) findViewById(R.id.tableLayout1);
            table_layout.removeAllViews();
            int cols = 3;
            int rows = 0;
            if (message2.size() % 3 == 0) {
                rows = message2.size() / 3;
            } else {
                rows = message2.size() / 3 + 1;
            }
            BuildTable(rows, cols, message2);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_result, menu);
		return true;
	}
//sample stuff ends

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

    private void BuildTable(int rows, int cols, ArrayList<String> imageURLs) {
        int index = 0;
        // outer for loop
        for (int i = 1; i <= rows; i++) {

            TableRow row = new TableRow(this);
            //row.setOnClickListener(mListener);
            //row.setId(1000 + i);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            row.setFocusable(true);
            row.setFocusableInTouchMode(true);
            row.setClickable(true);
            // inner for loop
            for (int j = 1; j <= cols; j++) {

                ImageView iv = new ImageView(this);
                iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                if (index<imageURLs.size()) {
                    Picasso.with(getApplicationContext()).load(imageURLs.get(index))
                            .placeholder(R.drawable.ic_launcher)
                            .error(R.drawable.camera_delete_icon)
                                    //.resizeDimen(80, 80)
                                    //.centerInside()
                            .into(iv);

                    row.addView(iv);

                }

                index++;
            }
            //row.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {onClickedRow(); }});
            table_layout.addView(row);
            }
        //table_layout.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {onClickedRow(); }});


        }
    protected void onURLError(String urlString) {
        Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
        //String result1 = result.description.get(1);//start with returning string description of 1st index
        intent.putExtra(EXTRA_MESSAGE3, urlString);
        startActivity(intent);
    }

    public void onClickedRow(){
        TableRow clickedRow = new TableRow(this);
        int m = table_layout.getChildCount();
        for (int n = 1 ; n < m ; n++)
        {
            if (table_layout.getChildAt(n).isFocused())
            {
                //table_layout.getChildAt(n).setBackgroundResource(R.drawable.highlightTableRow);
                clickedRow = (TableRow) table_layout.getChildAt(n);
                int j = clickedRow.getChildCount();
                for (int i = 0; i < j ; i++)
                {
                    switch(i)
                    {
                        case 0:
                            ImageView myField1 = (ImageView) clickedRow.getChildAt(i);
                            break;
                        case 1:
                            ImageView myField2 = (ImageView) clickedRow.getChildAt(i);
                            break;
                        case 2:
                            ImageView myField3 = (ImageView) clickedRow.getChildAt(i);
                            break;
                    }
                }
            }
        }
    }
    }


