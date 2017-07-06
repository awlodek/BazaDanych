package com.example.przemcio.bazadanych;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button addData;
    EditText eName;
    EditText eBrand;
    EditText eYear;
    EditText eid;
    BaseHelper dh;
    Button getdata;
    Button deletedata;
    Button modyfikuj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new BaseHelper(this);
        addData = (Button) findViewById(R.id.button);
        eName = (EditText) findViewById(R.id.editText);
        eBrand = (EditText) findViewById(R.id.editText2);
        eYear = (EditText) findViewById(R.id.editText3);
        getdata = (Button) findViewById(R.id.button2);
        deletedata = (Button) findViewById(R.id.button3);
        modyfikuj=(Button)findViewById(R.id.button4);
        eid = (EditText) findViewById(R.id.editText4);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dh.addData(eName.getText().toString(),eBrand.getText().toString(),eYear.getText().toString(),eid.getText().toString())){
                    Toast.makeText(MainActivity.this,"Added",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCursor kursor = dh.getData();
                if (kursor.getCount() > 0){
                    StringBuffer buff = new StringBuffer();
                    while (kursor.moveToNext()){
                        buff.append("id:"+kursor.getString(0)+"\n");
                        buff.append("nazwa::"+kursor.getString(1)+"\n");
                        buff.append("marka:"+kursor.getString(2)+"\n");
                        buff.append("rok:"+kursor.getString(3)+"\n");
                    }
                    showMessage("rekord",buff.toString());
                }
                else{
                    showMessage("brak rekordow","brak rekordow");
                }
            }
        });
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dh.delateData(eid.getText().toString()))
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });

        modyfikuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dh.delateData(eid.getText().toString()))
                {
                    if (dh.addData(eName.getText().toString(),eBrand.getText().toString(),eYear.getText().toString(),eid.getText().toString()))
                    {
                        Toast.makeText(MainActivity.this,"Modyfikacja",Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();

            }

        });


    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();

    }


}
