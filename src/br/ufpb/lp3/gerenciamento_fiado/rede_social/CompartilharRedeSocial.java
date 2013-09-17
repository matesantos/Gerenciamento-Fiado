package br.ufpb.lp3.gerenciamento_fiado.rede_social;


import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpb.lp3.gerenciamento_fiado.R;

public class CompartilharRedeSocial extends Activity {
	// SocialAuth Component
	private SocialAuthAdapter adapter = null;

	// Android Components
	private Button update = null;
	private EditText edit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compartilhar_rede_social);

		// Create Your Own Share Button
		Button share = (Button) findViewById(R.id.sharebutton);
		share.setText("Share");
		share.setTextColor(Color.WHITE);
		share.setBackgroundResource(R.drawable.button_gradient);
		
		update = (Button) findViewById(R.id.update);

		// Add it to Library
		adapter = new SocialAuthAdapter(new ResponseListener());

		// Add providers
		adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);

		// Enable Provider
		adapter.enable(share);
	}

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");

			// Get name of provider after authentication
			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareButton", "Provider Name = " + providerName);
			Toast.makeText(CompartilharRedeSocial.this,	providerName + " connected", Toast.LENGTH_LONG).show();

			update = (Button) findViewById(R.id.update);
			edit = (EditText) findViewById(R.id.editTextCompartilharTelaRedeSocial);

			// Please avoid sending duplicate message. Social Media Providers
			// block duplicate messages.

			update.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					adapter.updateStatus(edit.getText().toString(),	new MessageListener(), false);

				}
			});

		}

		@Override
		public void onError(SocialAuthError error) {
			Log.d("ShareButton", "Authentication Error: " + error.getMessage());
		}

		@Override
		public void onCancel() {
			Log.d("ShareButton", "Authentication Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Share-Button", "Dialog Closed by pressing Back Key");
		}

	}

	// To get status of message after authentication
	private final class MessageListener implements SocialAuthListener<Integer> {
		@Override
		public void onExecute(String provider, Integer t) {
			Integer status = t;
			if (status.intValue() == 200 || status.intValue() == 201
					|| status.intValue() == 204)
				Toast.makeText(CompartilharRedeSocial.this,
						"Menssagem postada em " + provider, Toast.LENGTH_LONG)
						.show();
			else
				Toast.makeText(CompartilharRedeSocial.this,
						"A menssagem não foi postada em" + provider,
						Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compartilhar_rede_social, menu);
		return true;
	}

}
