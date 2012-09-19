package org.example.contact_book;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Create extends Activity implements OnClickListener {
	Button save, refresh;
	EditText contact_name,contact_age,contact_number;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_contact);
		save = (Button)findViewById(R.id.save);
		refresh = (Button)findViewById(R.id.refresh);
		contact_name = (EditText)findViewById(R.id.create_name);
		contact_age = (EditText)findViewById(R.id.create_age);
		contact_number = (EditText)findViewById(R.id.create_number);
	    save.setOnClickListener(this);
	    refresh.setOnClickListener(this);
	}
	
	public void onClick(View v){
		switch (v.getId()){
		case R.id.save:
			boolean didItWork = true;
			try {
			String name = contact_name.getText().toString();
			int age = Integer.parseInt(contact_age.getText().toString());
			String number = contact_number.getText().toString();
			
			Database entry = new Database(this);
			entry.open();
			entry.createContact(name,age,number);
			entry.close();
			}
			catch (Exception e){
				didItWork = false;
			}
			finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Congrats!");
					TextView tv = new TextView(this);
					tv.setText("New Contact Created!");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
			
		case R.id.refresh:
			EditText age = (EditText) findViewById(R.id.create_age);
			EditText name = (EditText) findViewById(R.id.create_name);
			EditText number = (EditText) findViewById(R.id.create_number);
			age.setText("");
			name.setText("");
			number.setText("");
			break;
		}
	}
}

