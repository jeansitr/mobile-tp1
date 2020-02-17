package dim.uqac.jean_simon.TP1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView valueLastName = (TextView)findViewById(R.id.nomEditTxt);
        TextView valueName = (TextView)findViewById(R.id.prenomEditTxt);
        valueLastName.setText(R.string.nom);
        valueName.setText(R.string.prenom);

        Button button= (Button)findViewById(R.id.updateBtn);
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
    }
}
