package tw.edu.pu.o1073025.trafficlight;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity implements OnClickListener {
    GameSurfaceView GameSV;
    Handler handler;
    Runnable runnable = new Runnable() {
        public void run() {
            Canvas canvas = GameActivity.this.GameSV.getHolder().lockCanvas();
            GameActivity.this.GameSV.drawSomething(canvas);
            GameActivity.this.GameSV.getHolder().unlockCanvasAndPost(canvas);
            if (!GameActivity.this.GameSV.BoyMoving.booleanValue() || GameActivity.this.GameSV.CurrentLight != "Red") {
                GameActivity.this.handler.postDelayed(GameActivity.this.runnable, 50);
                return;
            }
            GameActivity.this.handler.removeCallbacks(GameActivity.this.runnable);
            GameActivity.this.GameSV.handlerLight.removeCallbacks(GameActivity.this.GameSV.runnableLight);
            GameActivity.this.GameOver();
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setRequestedOrientation(0);
        setContentView((int) C0264R.layout.activity_game);
        this.GameSV = (GameSurfaceView) findViewById(C0264R.C0266id.GameSV);
        Intent it = getIntent();
        this.GameSV.SetLightSec(it.getIntExtra("SecG", 0), it.getIntExtra("SecY", 0), it.getIntExtra("SecR", 0));
        this.handler = new Handler();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.GameSV.BoyMoving = Boolean.valueOf(true);
            this.handler.post(this.runnable);
        } else if (event.getAction() == 1) {
            this.GameSV.BoyMoving = Boolean.valueOf(false);
            this.handler.removeCallbacks(this.runnable);
        }
        return true;
    }

    public void GameOver() {
        new Builder(this).setTitle("遊戲結束").setMessage("不可以闖紅燈喔！").setIcon(C0264R.C0265drawable.boy8).setPositiveButton("結束系統", this).show();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        finish();
        //test
    }
}
