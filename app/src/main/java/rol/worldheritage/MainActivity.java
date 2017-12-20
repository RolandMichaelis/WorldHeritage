package rol.worldheritage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private rol.worldheritage.heritagePack heritagePack;
    Switch simpleSwitch;
    public String test;

    ListView simpleList;
    ArrayList<Item> heritageList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream source = getAssets().open("heritages.xml");
            Serializer serializer = new Persister();
            heritagePack = serializer.read(rol.worldheritage.heritagePack.class, source);
            //Toast.makeText(this, "Wow! Klappt!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //Toast.makeText(this, "Oh oh! heritagePack", Toast.LENGTH_LONG).show();
            Log.e(getClass().getSimpleName(), "loading levels threw exception", e);
        }
        simpleList = (ListView) findViewById(R.id.simpleListView);

        draw_bottom_line();
        list_calc();

    }
    public void onClick(final View view) {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        if(view.getId()==R.id.ic_logo1) {
            boolean but1 = sp.getBoolean("but1", false);
            if(!but1) {
                e.putBoolean("but1", true);
            } else {
                e.putBoolean("but1", false);
            }
            e.commit();
        }
        if(view.getId()==R.id.ic_logo2) {
            boolean but2 = sp.getBoolean("but2", false);
            if(!but2) {
                e.putBoolean("but2", true);
            } else {
                e.putBoolean("but2", false);
            }
            e.commit();
        }
        if(view.getId()==R.id.ic_logo3) {
            boolean but3 = sp.getBoolean("but3", false);
            if(!but3) {
                e.putBoolean("but3", true);
            } else {
                e.putBoolean("but3", false);
            }
            e.commit();
        }
        if(view.getId()==R.id.ic_logo4) {
            boolean but4 = sp.getBoolean("but4", false);
            if(!but4) {
                e.putBoolean("but4", true);
            } else {
                e.putBoolean("but4", false);
            }
            e.commit();
        }
        draw_bottom_line();
    }
    private void draw_bottom_line(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean but1 = sp.getBoolean("but1", false);
        boolean but2 = sp.getBoolean("but2", false);
        boolean but3 = sp.getBoolean("but3", false);
        boolean but4 = sp.getBoolean("but4", false);

        if(but1 && but2){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo1);
            image1.setImageResource(R.drawable.ic_culture);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo2);
            image2.setImageResource(R.drawable.ic_nature);
        }
        if(but1 && !but2){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo1);
            image1.setImageResource(R.drawable.ic_culture);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo2);
            image2.setImageResource(R.drawable.ic_nature_gray);
        }
        if(!but1 && but2){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo1);
            image1.setImageResource(R.drawable.ic_culture_gray);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo2);
            image2.setImageResource(R.drawable.ic_nature);
        }
        if(!but1 && !but2){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo1);
            image1.setImageResource(R.drawable.ic_culture);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo2);
            image2.setImageResource(R.drawable.ic_nature);
        }
        if(but3 && but4){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo3);
            image1.setImageResource(R.drawable.ic_in_progress);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo4);
            image2.setImageResource(R.drawable.ic_visited);
        }
        if(but3 && !but4){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo3);
            image1.setImageResource(R.drawable.ic_in_progress);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo4);
            image2.setImageResource(R.drawable.ic_visited_gray);
        }
        if(!but3 && but4){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo3);
            image1.setImageResource(R.drawable.ic_in_progress_gray);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo4);
            image2.setImageResource(R.drawable.ic_visited);
        }
        if(!but3 && !but4){
            ImageView image1 = (ImageView) this.findViewById(R.id.ic_logo3);
            image1.setImageResource(R.drawable.ic_in_progress);
            ImageView image2 = (ImageView) this.findViewById(R.id.ic_logo4);
            image2.setImageResource(R.drawable.ic_visited);
        }
        list_calc();
    }
    private void list_calc() {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean but1 = sp.getBoolean("but1", false);
        boolean but2 = sp.getBoolean("but2", false);
        boolean but3 = sp.getBoolean("but3", false);
        boolean but4 = sp.getBoolean("but4", false);
        if(!but1 && !but2) {
            but1=true;
            but2=true;
        }
        if(!but3 && !but4) {
            but3=true;
            but4=true;
        }



        int heritageCount = heritagePack.getHeritages().size();
        int statusPic;
        heritageList.clear();
        boolean written;
        for (int i = 0; i < heritageCount; i++) {
            written = false;
            Heritage w = heritagePack.getHeritages().get(i);
            boolean status = sp.getBoolean("status" + w.getId(), false);
            if (status) {
                statusPic = getResources().getIdentifier("ic_visited", "drawable", getPackageName());
            }
            else {
                statusPic = getResources().getIdentifier("ic_in_progress", "drawable", getPackageName());
            }
            String Name = "h" + w.getId();
            if(but3 && !status && !written) {
                if(but1 && w.getType().equals("C") && !written) {
                    heritageList.add(new Item(w.getName(), w.getText(), getResources().getIdentifier(Name, "drawable", getPackageName()), statusPic, w.getRegistered(), w.getType()));
                    written = true;
                }
                if(but2 && w.getType().equals("N") && !written) {
                    heritageList.add(new Item(w.getName(), w.getText(), getResources().getIdentifier(Name, "drawable", getPackageName()), statusPic, w.getRegistered(), w.getType()));
                    written = true;
                }
            }
            if(but4 && status && !written){
                if(but1 && w.getType().equals("C") && !written) {
                    heritageList.add(new Item(w.getName(), w.getText(), getResources().getIdentifier(Name, "drawable", getPackageName()), statusPic, w.getRegistered(), w.getType()));
                    written = true;
                }
                if(but2 && w.getType().equals("N") && !written) {
                    heritageList.add(new Item(w.getName(), w.getText(), getResources().getIdentifier(Name, "drawable", getPackageName()), statusPic, w.getRegistered(), w.getType()));
                    written = true;
                }
            }
        }
        MyAdapter myAdapter = new MyAdapter(this, R.layout.grid_view_items, heritageList);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_login(position);

                //Toast.makeText(getApplicationContext(), "test: "+test, Toast.LENGTH_SHORT).show();

            }

        });

    }


    public void dialog_login(int position) {
        final int p=position;
        Heritage w = heritagePack.getHeritages().get(p);
        final int nr=w.getId();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                new ContextThemeWrapper(this, android.R.style.Theme_DeviceDefault_Light_Dialog));

        LayoutInflater inflater = getLayoutInflater();

        final View dialogsViewNL = inflater.inflate(R.layout.dialog, null);

        final SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean status = sp.getBoolean("status" + nr, false);
        if(status) {
            simpleSwitch = (Switch) dialogsViewNL.findViewById(R.id.simpleSwitch);
            simpleSwitch.setChecked(true);
        }


        builder.setView(dialogsViewNL);
         builder.setTitle(w.getName())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(MainActivity.this, "Done = "+ p, Toast.LENGTH_LONG).show();
                        //dialog.dismiss();
                        simpleSwitch = (Switch) dialogsViewNL.findViewById(R.id.simpleSwitch);
                        String statusSwitch;
                        if (simpleSwitch.isChecked()){
                            SharedPreferences.Editor e = sp.edit();
                            e.putBoolean("status" + nr, true);
                            e.commit();
                            //statusSwitch="on";
                        }
                        else{
                            SharedPreferences.Editor e = sp.edit();
                            e.putBoolean("status" + nr, false);
                            e.commit();
                            //statusSwitch="off";
                        }

                        //Toast.makeText(getApplicationContext(), "Switch :" + statusSwitch, Toast.LENGTH_LONG).show(); // display the current state for switch's

                        dialog.cancel();
                        list_calc();
                    }
                });



        // create alert dialog
        final AlertDialog alertDialog = builder.create();
        final TextView textView = (TextView) dialogsViewNL.findViewById(R.id.text_registered);
        final TextView textView2 = (TextView) dialogsViewNL.findViewById(R.id.text_adress);

        textView.setText("Aufnahme: "+w.getRegistered());
        textView2.setText("Adresse: "+w.getNavi());
        //textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        /*dialogsViewNL.findViewById(R.id.forgot_pw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, getString(R.string.send_password), Toast.LENGTH_LONG).show();
                editTextEmail=email.getText().toString().toLowerCase();
                serverForgotPassword(editTextEmail);
                //alertDialog.dismiss();
            }
        });*/
        // show it
        alertDialog.show();

    }


/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_culture:
                    mTextMessage.setText(R.string.title_culture);
                    return true;
                case R.id.navigation_nature:
                    mTextMessage.setText(R.string.title_nature);
                    return true;
                case R.id.navigation_in_progress:
                    mTextMessage.setText(R.string.title_in_progress);
                    return true;
                case R.id.navigation_visited:
                    mTextMessage.setText(R.string.title_visited);
                    return true;
            }
            return false;
        }
    };*/

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream source = getAssets().open("heritages.xml");
            Serializer serializer = new Persister();
            heritagePack = serializer.read(rol.worldheritage.heritagePack.class, source);
            Toast.makeText(this, "Wow! Klappt!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Oh oh! heritagePack", Toast.LENGTH_LONG).show();
            Log.e(getClass().getSimpleName(), "loading levels threw exception", e);
        }

/*        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }*/

}
