package com.github.singleunderscore.sharetoclipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * The activity that receives the send intents and copies the text to the clipboard.
 */
public class CopyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        // make sure this is a share intent
        if (Intent.ACTION_SEND.equals(intent.getAction())) {

            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText == null) {
                // don't modify clipboard if null sharedText
                Toast.makeText(getApplicationContext(), "Null text shared", Toast.LENGTH_SHORT).show();
            } else {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(ClipData.newPlainText("Shared Text", sharedText));

                Toast.makeText(getApplicationContext(), sharedText, Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}
