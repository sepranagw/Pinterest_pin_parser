package myrdiodemo.com.pinterest_pin_parser;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;


import android.os.AsyncTask;

import android.content.Intent;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    public final static String strikeIronUserName = "username@yourdomain.com";
    public final static String strikeIronPassword = "password";
    public final static String apiURL = "http://pinterest_pin_parser.myrdiodemo.com/Stuff/stuff/VerifyUser";
    public final static String EXTRA_MESSAGE1 = "default1";
    public final static String EXTRA_MESSAGE2 = "default2";

    private class userVerificationResult {
        public ArrayList<String> description = new ArrayList<String>();
        public ArrayList<String> imageURL = new ArrayList<String>();
    }

    private class CallAPI extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String resultToDisplay = "";
            InputStream in = null;
            userVerificationResult result = null;
            InputStreamReader inputStreamReader = null;
            //BufferedReader bufferedReader = null;
            JsonReader jsonReader = null;

            // HTTP Get
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
                inputStreamReader = new InputStreamReader(in);
                //bufferedReader = new BufferedReader(inputStreamReader);
                jsonReader = new JsonReader(inputStreamReader);
                result = parseJSON(jsonReader, urlString);
                if (result != null) {
                    onPostExecute(result);
                }

            }
            catch (IOException e) {

                Log.e("HTTP GET22:", e.toString());
                runOnUiThread(new Runnable(){
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Invalid User. Please try another name",Toast.LENGTH_LONG).show();
                    }
                });
                return e.getMessage().toString();
            }
            catch (IllegalStateException e){
                System.err.println(e.toString());
                runOnUiThread(new Runnable(){
                    public void run() {
                        Toast.makeText(getApplicationContext(), "System error. Please try again later.",Toast.LENGTH_LONG).show();
                    }
                });
                return e.getMessage().toString();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                runOnUiThread(new Runnable(){
                    public void run() {
                        Toast.makeText(getApplicationContext(), "System error. Please try again later.",Toast.LENGTH_LONG).show();
                    }
                });
                return e.getMessage().toString();
            }
            // Parse JSON
            return "success";//place holder return statement
        }

        protected void onPostExecute(userVerificationResult result) {
            Intent intent = new Intent(getApplicationContext(), ResultActivityGrid.class);
            //String result1 = result.description.get(1);//start with returning string description of 1st index
            intent.putStringArrayListExtra(EXTRA_MESSAGE1, result.description);
            intent.putStringArrayListExtra(EXTRA_MESSAGE2, result.imageURL);
            startActivity(intent);
        }

        private userVerificationResult parseJSON(JsonReader jsonReader, String urlString) throws IOException {
            //int eventType = parser.getEventType();
            userVerificationResult result = new userVerificationResult();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                final String name = jsonReader.nextName();
                final boolean isNull = jsonReader.peek() == JsonToken.NULL;
                if (name.equals("data") && !isNull) {
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        final String innerName = jsonReader.nextName();
                        final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
                        if (innerName.equals("pins") && !isInnerNull) {
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                jsonReader.beginObject();
                                while (jsonReader.hasNext()) {
                                    final String innerInnerName = jsonReader.nextName();
                                    final boolean isInnerInnerNull = jsonReader.peek() == JsonToken.NULL;
                                    if (innerInnerName.equals("description") && !isInnerInnerNull) {
                                        result.description.add(jsonReader.nextString());
                                    } else if (innerInnerName.equals("images") && !isInnerInnerNull) {
                                        jsonReader.beginObject();
                                        while (jsonReader.hasNext()) {
                                            final String sizeName = jsonReader.nextName();
                                            final boolean isSizeNull = jsonReader.peek() == JsonToken.NULL;
                                            if (sizeName.equals("237x") && !isSizeNull) {
                                                jsonReader.beginObject();
                                                while (jsonReader.hasNext()) {
                                                    final String imageName = jsonReader.nextName();
                                                    final boolean isImageNull = jsonReader.peek() == JsonToken.NULL;
                                                    if (imageName.equals("url") && !isImageNull) {
                                                        result.imageURL.add(jsonReader.nextString());
                                                    } else {
                                                        jsonReader.skipValue();
                                                    }
                                                }
                                                jsonReader.endObject();
                                            } else {
                                                jsonReader.skipValue();
                                            }
                                        }
                                        jsonReader.endObject();
                                    } else {
                                        jsonReader.skipValue();
                                    }
                                }
                                jsonReader.endObject();
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (name.equals("data") && isNull) {
                    Toast.makeText(getBaseContext(), "Invalid User. Please try another name.", Toast.LENGTH_LONG).show();
                    //onURLError(urlString);

                    result.description.add("Invalid");
                    result.imageURL.add(urlString);


                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();

            return result;
        }


    } // end CallAPI



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText)findViewById(R.id.enter_user);
        //editText.setText(*"");
        //editText.getText().clear();
        //editText.setOnClickListener(this);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
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
            Toast.makeText(getBaseContext(), "Version 1.0, English, Creator: Corinne Nakashima", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This is the method that is called when the submit button is clicked
    public void verifyUser(View view) {
        EditText userEditText = (EditText) findViewById(R.id.enter_user);
        String user = userEditText.getText().toString();

        if (user != null && !user.isEmpty()) {
            user = user.replaceAll(" ", "");
            user = user.toLowerCase();
            String urlString = "http://widgets.pinterest.com/v3/pidgets/users/" + user + "/pins/";
            new CallAPI().execute(urlString);

        } else {
            Toast.makeText(getBaseContext(), "Invalid user name. Please check spelling or try another name.", Toast.LENGTH_LONG).show();
        }


    }



}



