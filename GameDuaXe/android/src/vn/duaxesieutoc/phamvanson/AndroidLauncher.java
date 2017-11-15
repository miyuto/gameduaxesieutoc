package vn.duaxesieutoc.phamvanson;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication implements AndroidInterfaces {
	final AndroidLauncher context=this;
	Handler handler;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainGame(this), config);
	}

	@Override
	public void toast(final String t) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context,t,Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void showDialog(final String title,final String mes) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle(title);
				builder.setMessage(mes);
				builder.setCancelable(false);
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						System.exit(0);
					}
				});
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				});
				builder.show();
			}
		});
	}
}

