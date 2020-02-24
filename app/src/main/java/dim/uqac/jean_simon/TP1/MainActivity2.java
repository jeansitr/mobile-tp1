package dim.uqac.jean_simon.TP1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        final Intent intent = getIntent();
        String message = intent.getStringExtra("extra_message");

        final EditText messageTxt = (EditText)findViewById(R.id.messageEditText);
        messageTxt.setText(message);

        Button returnBtn = (Button)findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("extra_message", messageTxt.getText());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
