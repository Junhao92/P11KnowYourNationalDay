package com.example.a14049472.p11_knowyournationalday;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);
        String item[] = new String [] {"Singapore National Days","Singapore is 53-year-old","Theme is We Are Singapore"};
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,item);
        lv.setAdapter(adapter);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout accesscode = (LinearLayout)inflater.inflate(R.layout.accesscode,null);
        final EditText etPassphrase = (EditText) accesscode.findViewById(R.id.editTextPassPhrase);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setView(accesscode)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(etPassphrase.getText().toString().equals("738964")){
                            Toast.makeText(MainActivity.this, "you had entered", Toast.LENGTH_SHORT).show();

                        }else{
                            finish();
                        }

                    }
                })
                .setNegativeButton("NO ACCESS CODE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"you had close",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.quit){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    .setPositiveButton("QUIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("NOT REALLY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"you clicked no",Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if(item.getItemId()==R.id.sendtofriends){
            String[] list = new String[]{"Email","SMS"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend?")
                    .setItems(list, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Toast.makeText(MainActivity.this, "You said SMS",
                                        Toast.LENGTH_LONG).show();
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"jason_lim@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,"Test Email from 347");
                                email.setType("message/rfc822");
                                startActivity(Intent.createChooser(email,"Choose an Email client : "));

                            }else {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + 97227820));
                                intent.putExtra("sms_body", "message");
                                startActivity(intent);
                                    // stops the action from proceeding further as permission not
                                    //  granted yet
                                    return;
                                }


                                // Create all messages URI
                                Uri uri = Uri.parse("content://sms");

                                Toast.makeText(MainActivity.this, "You click sms",
                                        Toast.LENGTH_LONG).show();
                            }


                    });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();



                    }else if (item.getItemId()==R.id.quiz){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
