package dim.uqac.jean_simon.TP1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.net.Uri;
import android.app.Notification;
import android.webkit.ServiceWorkerClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.CheckBox;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private NotificationManager nm;
    private int count;
    private static String CHANNEL_ID = "id_257";
    private static String CHANNEL_NAME = "channel_257";
    private static String CHANNEL_DESCRIPTION = "description_257";
    private static int NOTIFICATION_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DIM", "onCreate");
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            TextView LastNameTxt = (TextView)findViewById(R.id.nom);
            TextView NameTxt = (TextView)findViewById(R.id.prenom);
            EditText prenom = (EditText)findViewById(R.id.prenomEditTxt);
            EditText nom = (EditText)findViewById(R.id.nomEditTxt);

            prenom.setText(NameTxt.getText());
            nom.setText(LastNameTxt.getText());

            Button button = (Button)findViewById(R.id.updateBtn);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    TextView displayLastName = (TextView)findViewById(R.id.nom);
                    TextView valueLastName = (TextView)findViewById(R.id.nomEditTxt);
                    TextView displayName = (TextView)findViewById(R.id.prenom);
                    TextView valueName = (TextView)findViewById(R.id.prenomEditTxt);

                    displayName.setText(valueName.getText());
                    displayLastName.setText(valueLastName.getText());
                }
            });

            CheckBox signatureCheckBox = (CheckBox)findViewById(R.id.signatureCheckBox);
            signatureCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ImageView signature = (ImageView)findViewById(R.id.signatureImage);
                    if(isChecked){
                        signature.setVisibility(View.VISIBLE);
                    }
                    else{
                        signature.setVisibility(View.INVISIBLE);
                    }
                }
            });

            Switch genderSwitch = (Switch)findViewById(R.id.genderSwitch);
            genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    TextView gender = (TextView)findViewById(R.id.sexe);
                    if(isChecked){
                        gender.setText("M");
                    }
                    else{
                        gender.setText("F");
                    }
                }
            });

            Button button1 = (Button)findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowToast();
                }
            });

            Button button2 = (Button)findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    creerNotification(view);
                }
            });

            Button button3 = (Button)findViewById(R.id.button3);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse("https://www.google.ca");
                    Intent webItent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webItent);
                }
            });

            Button button4 = (Button)findViewById(R.id.button4);
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("extra_message", "Bienvenue dans l'activitée #2 !!");
                    startActivityForResult(intent, 2);
                }
            });
        }

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
        channel.setDescription(CHANNEL_DESCRIPTION);
        nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(channel);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("DIM", "onSaveInstanceState");
        TextView lastNameTxt = (TextView)findViewById(R.id.nom);
        TextView nameTxt = (TextView)findViewById(R.id.prenom);
        ImageView signatureImg = (ImageView)findViewById(R.id.signatureImage);
        TextView genderTxt = (TextView)findViewById(R.id.sexe);

        outState.putCharSequence("nom", lastNameTxt.getText());
        outState.putCharSequence("prenom", nameTxt.getText());
        outState.putBoolean("signature", signatureImg.getVisibility() == View.VISIBLE);
        outState.putBoolean("gender", genderTxt.getText().equals("M") || genderTxt.getText().equals("H"));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("DIM", "onRestoreInstanceState");
        TextView LastNameTxt = (TextView)findViewById(R.id.nom);
        TextView NameTxt = (TextView)findViewById(R.id.prenom);
        TextView sexeTxt = (TextView)findViewById(R.id.sexe);
        ImageView signatureImage = (ImageView)findViewById(R.id.signatureImage);

        Boolean sexeBool = savedInstanceState.getBoolean("gender");
        Boolean signatureBool = savedInstanceState.getBoolean("signature");
        CharSequence valueNom = savedInstanceState.getCharSequence("nom");
        CharSequence valuePrenom = savedInstanceState.getCharSequence("prenom");

        LastNameTxt.setText(valueNom);
        NameTxt.setText(valuePrenom);

        if(sexeBool){
            sexeTxt.setText(R.string.sexe);
        }
        else{
            sexeTxt.setText("F");
        }

        if(signatureBool){
            signatureImage.setVisibility(View.VISIBLE);
        }
        else{
            signatureImage.setVisibility(View.INVISIBLE);
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Switch genderSwitch = (Switch)findViewById(R.id.genderSwitch);
            CheckBox signatureCheckBox = (CheckBox)findViewById(R.id.signatureCheckBox);
            EditText prenom = (EditText)findViewById(R.id.prenomEditTxt);
            EditText nom = (EditText)findViewById(R.id.nomEditTxt);

            prenom.setText(valuePrenom);
            nom.setText(valueNom);
            genderSwitch.setChecked(sexeBool);
            signatureCheckBox.setChecked(signatureBool);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DIM", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("DIM", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("DIM", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DIM", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DIM", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DIM", "onDestroy");
    }

    public void ShowToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.toastRoot));

        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }

    public void creerNotification(View view){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification n = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Message - TP1 INF257")
                .setContentText("Voici une belle notification :)")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setNumber(count++)
                .setAutoCancel(true)
                .setOngoing(true)
                .setFullScreenIntent(pIntent, true)
                .setStyle(new Notification.BigTextStyle().bigText("IMPORTANT"))
                .setTicker("Hey! Je suis le TP1 :D")
                .addAction(R.mipmap.ic_launcher, "Toast!", actionIntent)
                .build();

        nm.notify(NOTIFICATION_ID, n);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItem1:
            {
                Log.i("DIM", "Menu item 1 clicked");
                return true;
            }
            case R.id.menuItem2:
            {
                Log.i("DIM", "Menu item 2 clicked");
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            CharSequence response = data.getCharSequenceExtra("extra_message");
            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
        }
    }
}