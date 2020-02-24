package dim.uqac.jean_simon.TP1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
        }
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
}
