package com.sipsakgbtab.sipsakgbt.ipakgbt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    String singleLine="";
    BufferedReader reader=null;
    Statement st;
    Connection con = null;


    public static String OrganizasyonKOD = "";
    public static String Organizasyonid = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(com.sipsakgbtab.sipsakgbt.ipakgbt.R.layout.activity_main);




        try {


            ImageView i = (ImageView)findViewById(R.id.imageView);
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://www.nvmvzv.com/sipsakgbt.png").getContent());

            i.setImageBitmap(bitmap);


        } catch (IOException e) {
            e.printStackTrace();
        }



        Button btnClickMe2 = (Button) findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.button);
        btnClickMe2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {



                try{

                    // CALL GetText method to make post method call


                    GetText();





                }
                catch(Exception ex)
                {

                }


            }
        });

    }


    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user

        final TextView DavetKOD = (TextView)findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.editText);

        final TextView GorevliKOD = (TextView)findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.editText2);

        final TextView GorevliSIFRE = (TextView)findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.editText3);


        String data = URLEncoder.encode("DavetKOD", "UTF-8")
                + "=" + URLEncoder.encode(DavetKOD.getText().toString(), "UTF-8");

        data += "&" + URLEncoder.encode("GorevliKOD", "UTF-8") + "="
                + URLEncoder.encode(GorevliKOD.getText().toString(), "UTF-8");

        data += "&" + URLEncoder.encode("GorevliSIFRE", "UTF-8")
                + "=" + URLEncoder.encode(GorevliSIFRE.getText().toString(), "UTF-8");



        // Send data
        try
        {

            URL url = new URL("http://www.atcnglr.mobi/sipsakgbt/index.php");



            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(60000);
            conn.setConnectTimeout(60000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
            conn.setDoInput(true);
            conn.setDoOutput(true);


            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            out.writeBytes(data);

            out.flush();

            //out.close();
            // Defined URL  where to send data


            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            //Read Server Response
            while((line = reader.readLine()) != null)
            {


                // Append server response in string
                sb.append(line);


                //TextView mySonucTextView = (TextView)findViewById(R.id.EditViewText);
                //mySonucTextView.setText(line);

                String[] separated = line.split("@");

                //    getDURAKString(separated[0].toString());

                //    getTAKSIString(separated[1].toString());


                OrganizasyonKOD = separated[1].toString();
                Organizasyonid = separated[2].toString();


                TextView mySonucTextView = (TextView)findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.EditViewText);
                mySonucTextView.setText(OrganizasyonKOD + " " + Organizasyonid);


                //Intent intent = new Intent(this, SecondActivity.class);
                //startActivity(intent);



            }
            /////////////////////////////////////////////////////////////////


            ///////////////////////////////////////////////////////////////////

        }
        catch(Exception ex)
        {

            ex.printStackTrace();

            TextView mySonucTextView = (TextView)findViewById(com.sipsakgbtab.sipsakgbt.ipakgbt.R.id.EditViewText);
            mySonucTextView.setText("Oturum Açma Hatası");

        }
        finally
        {

        }


    }



}
