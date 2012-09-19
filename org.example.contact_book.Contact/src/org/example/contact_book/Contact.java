package org.example.contact_book;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class Contact extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View aboutButton = findViewById(R.id.button_about);
        aboutButton.setOnClickListener(this);
        View createButton = findViewById(R.id.button_create_contact);
        createButton.setOnClickListener(this);
        View viewButton = findViewById(R.id.viewinfo);
        viewButton.setOnClickListener(this);
    }

    public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_about:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		// More buttons go here (if any) ...
		case R.id.viewinfo:
			i = new Intent(this,ContactView.class);
			startActivity(i);
			break;
		case R.id.button_create_contact:
			i = new Intent(this, Create.class);
			startActivity(i);
			break;
		}
		}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
