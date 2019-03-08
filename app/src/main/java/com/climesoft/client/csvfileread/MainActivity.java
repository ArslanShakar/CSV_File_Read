package com.climesoft.client.csvfileread;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private static int SELECT_CSV_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvCSVData);
    }

    public void importAndReadCSV_FileData(View view) throws IOException {

        openAndGetFilePath();

        /*try {
            InputStream inputStream = getResources().openRawResource(R.raw.sample_csv);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";
            StringBuffer data = new StringBuffer();
            //reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Log.d("MyTag", line);
                if (tokens[0].length() > 0) {
                    data.append("ID : " + tokens[0]);
                } else {
                    data.append("ID : " + null);
                }
                if (tokens[1].length() > 0) {
                    data.append(" -- Name : " + tokens[1]);
                } else {
                    data.append(" -- Name : " + null);
                }

                data.append("\n");
            }

            tvData.setText(data);
        } catch (Exception e) {
            tvData.setText(e.toString());
        }*/
    }

    public void openAndGetFilePath()
    {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("*/*");
        startActivityForResult(intent, SELECT_CSV_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if(requestCode == SELECT_CSV_FILE)
        {
            if(resultCode == RESULT_OK) {
                String filePath = resultData.getData().getPath();

                try {

                    FileInputStream inputStream = new FileInputStream(filePath);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

                    String line = "";
                    StringBuffer data = new StringBuffer();
                    //reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        String[] tokens = line.split(",");
                        if (tokens[0].length() > 0) {
                            data.append("ID : " + tokens[0]);
                        } else {
                            data.append("ID : " + null);
                        }
                        if (tokens[1].length() > 0) {
                            data.append(" -- Name : " + tokens[1]);
                        } else {
                            data.append(" -- Name : " + null);
                        }

                        data.append("\n");
                    }

                    tvData.setText(data);
                } catch (Exception e) {
                    tvData.setText(e.toString());
                    Log.d("MyTag", e.toString());
                }

                Toast.makeText(MainActivity.this, filePath , Toast.LENGTH_LONG).show();

            }
        }
    }
}
