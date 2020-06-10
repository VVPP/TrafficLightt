package tw.edu.pu.o1073025.trafficlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/* renamed from: tw.edu.pu.csim.tcyang.trafficlight.MainActivity */
public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setRequestedOrientation(0);
        setContentView((int) C0264R.layout.activity_main);
    }

    public void StartGame(View v) {
        Intent it = new Intent();
        it.setClass(this, GameActivity.class);
        EditText SecY = (EditText) findViewById(C0264R.C0266id.editTextYellow);
        EditText SecR = (EditText) findViewById(C0264R.C0266id.editTextRed);
        String str = "SecG";
        it.putExtra(str, Integer.valueOf(((EditText) findViewById(C0264R.C0266id.editTextGreen)).getText().toString()));
        it.putExtra("SecY", Integer.valueOf(SecY.getText().toString()));
        it.putExtra("SecR", Integer.valueOf(SecR.getText().toString()));
        startActivity(it);
        finish();
    }

    public void EndApp(View v) {
        finish();
    }
}
