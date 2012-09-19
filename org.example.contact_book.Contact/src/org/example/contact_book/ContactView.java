package org.example.contact_book;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.contact_view);
	TextView tv = (TextView) findViewById(R.id.Contact_info);
	Database info = new Database(this);
	info.open();
	String data = info.getData();
	info.close();
	tv.setText(data);
}
}

