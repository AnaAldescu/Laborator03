package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class PhoneDialerActivity extends Activity {
	final static int CONTACTS_MANAGER_REQUEST_CODE = 10;
	final static String phone_error = "Phone Error";
	
	private class ButtonClick implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			Button btn = (Button) view;
			String btnValue = btn.getText().toString();
			EditText phoneNumber = (EditText) findViewById(R.id.editTextNumber);
			
			phoneNumber.setText(phoneNumber.getText() + btnValue);
		}
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        
        ButtonClick btnClick = new ButtonClick();
        
        int[] btnIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
        				R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10,
        				R.id.button11, R.id.button12};
        
        for (int id : btnIds) {
        	Button btn = (Button) findViewById(id);
        	
        	btn.setOnClickListener(btnClick);
        }
        
        ImageButton backspace = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton call = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton hangup = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton addContact = (ImageButton) findViewById(R.id.imageButton4);
        
        backspace.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editText = (EditText) findViewById(R.id.editTextNumber);
				String phoneNumber = editText.getText().toString();
				
				if (phoneNumber.length() > 0) {
					editText.setText(phoneNumber.subSequence(0, phoneNumber.length() - 1));
				}
			}
		});
        
        call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editText = (EditText) findViewById(R.id.editTextNumber);
				String phoneNumber = editText.getText().toString();
				
				Intent intent = new Intent(Intent.ACTION_CALL);
					
				intent.setData(Uri.parse("tel:" + phoneNumber));
				startActivity(intent);
			}
		});
        
        hangup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        addContact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText editText = (EditText) findViewById(R.id.editTextNumber);
				String phoneNumber = editText.getText().toString();
				
				if (phoneNumber.length() > 0) {
					Intent intent = new Intent("ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity");
					  
					intent.putExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
					startActivityForResult(intent, CONTACTS_MANAGER_REQUEST_CODE);
				} else {
					Toast.makeText(getApplication(), phone_error, Toast.LENGTH_LONG).show();
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phone_dialer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
