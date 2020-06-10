package tw.edu.pu.o1073025.trafficlight;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;


public class GameSurfaceView extends SurfaceView implements Callback {
    int BGmoveX = 0;
    Bitmap Boy;
    Boolean BoyMoving = Boolean.valueOf(false);
    int CurrentCountDown;
    String CurrentLight;
    Rect DestRect;
    int GreenLightSec;
    int RedLightSec;
    Bitmap Road;
    Rect SrcRect;
    int YellowLightSec;


    float f33h;
    Handler handlerLight;
    Paint paint;
    float ratio;
    Runnable runnableLight = new Runnable() {
        public void run() {
            GameSurfaceView.this.CurrentCountDown--;
            if (GameSurfaceView.this.CurrentCountDown == -1) {
                String str = "Yellow";
                String str2 = "Green";
                if (GameSurfaceView.this.CurrentLight == str2) {
                    GameSurfaceView.this.CurrentLight = str;
                    GameSurfaceView gameSurfaceView = GameSurfaceView.this;
                    gameSurfaceView.CurrentCountDown = gameSurfaceView.YellowLightSec;
                } else if (GameSurfaceView.this.CurrentLight == str) {
                    GameSurfaceView.this.CurrentLight = "Red";
                    GameSurfaceView gameSurfaceView2 = GameSurfaceView.this;
                    gameSurfaceView2.CurrentCountDown = gameSurfaceView2.RedLightSec;
                } else {
                    GameSurfaceView.this.CurrentLight = str2;
                    GameSurfaceView gameSurfaceView3 = GameSurfaceView.this;
                    gameSurfaceView3.CurrentCountDown = gameSurfaceView3.GreenLightSec;
                }
            }
            Canvas canvas = GameSurfaceView.this.surfaceHolder.lockCanvas(null);
            GameSurfaceView.this.drawSomething(canvas);
            GameSurfaceView.this.surfaceHolder.unlockCanvasAndPost(canvas);
            GameSurfaceView.this.handlerLight.postDelayed(GameSurfaceView.this.runnableLight, 1000);
        }
    };
    int step = 1;
    SurfaceHolder surfaceHolder;


    float f34w;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        this.surfaceHolder = holder;
        holder.addCallback(this);
        this.Road = BitmapFactory.decodeResource(getResources(), C0264R.C0265drawable.road);
        this.Boy = BitmapFactory.decodeResource(getResources(), C0264R.C0265drawable.boy1);
        this.paint = new Paint();
        Handler handler = new Handler();
        this.handlerLight = handler;
        handler.post(this.runnableLight);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
        Canvas canvas = surfaceHolder2.lockCanvas(null);
        drawSomething(canvas);
        surfaceHolder2.unlockCanvasAndPost(canvas);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i1, int i2) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
        this.handlerLight.removeCallbacks(this.runnableLight);
    }

    public void drawSomething(Canvas canvas) {
        DrawRoad(canvas);
        DrawBoy(canvas);
        DrawLight(canvas);
    }

    public void DrawRoad(Canvas canvas) {
        this.SrcRect = new Rect(0, 0, this.Road.getWidth(), this.Road.getHeight());
        this.ratio = ((float) this.Road.getHeight()) / (((float) canvas.getHeight()) * 0.95f);
        this.f34w = ((float) this.Road.getWidth()) / this.ratio;
        this.f33h = ((float) this.Road.getHeight()) / this.ratio;
        if (this.BoyMoving.booleanValue()) {
            this.BGmoveX -= 5;
        }
        int BGnewX = ((int) this.f34w) + this.BGmoveX;
        if (BGnewX <= 0) {
            this.BGmoveX = 0;
            Rect rect = new Rect(0, 0, (int) this.f34w, (int) this.f33h);
            this.DestRect = rect;
            canvas.drawBitmap(this.Road, this.SrcRect, rect, null);
            return;
        }
        int i = this.BGmoveX;
        Rect rect2 = new Rect(i, 0, (int) (this.f34w + ((float) i)), (int) this.f33h);
        this.DestRect = rect2;
        canvas.drawBitmap(this.Road, this.SrcRect, rect2, null);
        Rect rect3 = new Rect(BGnewX, 0, (int) (this.f34w + ((float) BGnewX)), (int) this.f33h);
        this.DestRect = rect3;
        canvas.drawBitmap(this.Road, this.SrcRect, rect3, null);
    }

    public void DrawBoy(Canvas canvas) {
        if (this.BoyMoving.booleanValue()) {
            int i = this.step + 1;
            this.step = i;
            if (i > 8) {
                this.step = 1;
            }
            Resources resources = getResources();
            StringBuilder sb = new StringBuilder();
            sb.append("boy");
            sb.append(this.step);
            this.Boy = BitmapFactory.decodeResource(getResources(), resources.getIdentifier(sb.toString(), "drawable", getContext().getPackageName()));
        }
        this.SrcRect = new Rect(0, 0, this.Boy.getWidth(), this.Boy.getHeight());
        float h = ((float) this.Boy.getHeight()) / this.ratio;
        float w0 = ((float) canvas.getHeight()) * 0.2f;
        float h0 = (((float) canvas.getHeight()) * 0.93f) - h;
        Rect rect = new Rect((int) w0, (int) h0, (int) (w0 + (((float) this.Boy.getWidth()) / this.ratio)), (int) (h0 + h));
        this.DestRect = rect;
        canvas.drawBitmap(this.Boy, this.SrcRect, rect, null);
        this.paint.setColor(-16776961);
        this.paint.setStyle(Style.FILL);
        this.paint.setTextSize((float) ((canvas.getHeight() * 60) / 1080));
        this.paint.setAntiAlias(true);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("圖片編號：");
        sb2.append(String.valueOf(this.step));
        String sb3 = sb2.toString();
        double height = (double) canvas.getHeight();
        Double.isNaN(height);
        canvas.drawText(sb3, 0.0f, (float) ((int) (height * 0.1d)), this.paint);
    }

    public void DrawLight(Canvas canvas) {
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        int r = (canvas.getHeight() * 100) / 1080;
        canvas.drawRect((float) ((canvas.getWidth() - (r * 2)) - 16), 0.0f, (float) canvas.getWidth(), (float) ((r * 6) + 30), this.paint);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(5.0f);
        this.paint.setColor(-16711936);
        canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) ((r * 5) + 20), (float) r, this.paint);
        this.paint.setColor(InputDeviceCompat.SOURCE_ANY);
        canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) ((r * 3) + 10), (float) r, this.paint);
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) r, (float) r, this.paint);
        this.paint.setStyle(Style.FILL_AND_STROKE);
        this.paint.setTextSize((float) r);
        String str = this.CurrentLight;
        if (str == "Green") {
            this.paint.setColor(-16711936);
            canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) ((r * 5) + 20), (float) r, this.paint);
            this.paint.setColor(-16776961);
            canvas.drawText(String.valueOf(this.CurrentCountDown), ((float) canvas.getWidth()) - (((float) r) * 1.5f), (((float) r) * 5.5f) + 10.0f, this.paint);
        } else if (str == "Yellow") {
            this.paint.setColor(InputDeviceCompat.SOURCE_ANY);
            canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) ((r * 3) + 10), (float) r, this.paint);
            this.paint.setColor(-16776961);
            canvas.drawText(String.valueOf(this.CurrentCountDown), ((float) canvas.getWidth()) - (((float) r) * 1.5f), (((float) r) * 3.5f) + 5.0f, this.paint);
        } else {
            this.paint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawCircle((float) ((canvas.getWidth() - r) - 8), (float) r, (float) r, this.paint);
            this.paint.setColor(-16776961);
            canvas.drawText(String.valueOf(this.CurrentCountDown), ((float) canvas.getWidth()) - (((float) r) * 1.5f), ((float) r) * 1.5f, this.paint);
        }
    }

    public void SetLightSec(int GreenSec, int YellowSec, int RedSec) {
        this.GreenLightSec = GreenSec;
        this.YellowLightSec = YellowSec;
        this.RedLightSec = RedSec;
        this.CurrentLight = "Green";
        this.CurrentCountDown = GreenSec + 1;
    }
}
