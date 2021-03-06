package com.hmkcode.android.gcm;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    TextView textProjectNo;
    Button btnRegId;
    EditText etRegId;
	GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "570684116140";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textProjectNo = (TextView) findViewById(R.id.textProjectNo);
        btnRegId = (Button) findViewById(R.id.btnGetRegId);
        etRegId = (EditText) findViewById(R.id.etRegId);

        textProjectNo.setText(PROJECT_NUMBER);
        btnRegId.setOnClickListener(this);
    }

    public void getRegId(){
    	new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM",  msg);


                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }

	@Override
	public void onClick(View v) {
		getRegId();
	}
    
}
