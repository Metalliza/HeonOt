package io.github.lee0701.heonot;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.github.lee0701.heonot.inputmethod.InputMethod;

public abstract class SettingsActivity extends AppCompatActivity {

	public static final String EXTRA_METHOD_ID = "io.github.lee0701.heonot.METHOD_ID";

	HeonOt heonOt;

	List<InputMethod> inputMethods = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		heonOt = new HeonOt(this);
		heonOt.init();
		try {
			inputMethods.addAll(heonOt.getInputMethodsCloned());
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_apply:
			heonOt.setInputMethods(inputMethods);
			File methodsDir = new File(getFilesDir(), "methods");
			heonOt.storeMethods(methodsDir);
			Snackbar.make(findViewById(R.id.toolbar), R.string.settings_saved, Snackbar.LENGTH_SHORT).show();
			HeonOt instance;
			if((instance = HeonOt.getInstance()) != null) {
				instance.init();
				instance.setInputView(instance.onCreateInputView());
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
