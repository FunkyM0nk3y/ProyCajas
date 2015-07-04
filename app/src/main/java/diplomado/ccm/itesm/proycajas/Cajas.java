package diplomado.ccm.itesm.proycajas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;


public class Cajas extends ActionBarActivity {

    private Button btnDibuja;
    private ImageView contenedor;
    private SeekBar sbCajas;
    private TextView tvNumCajas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajas);

        btnDibuja = (Button) findViewById(R.id.buttonCajas);
        contenedor = (ImageView) findViewById(R.id.imageViewCajas);
        sbCajas = (SeekBar) findViewById(R.id.seekBarCajas);

        sbCajas.setMax(50);
        btnDibuja.setOnClickListener(listenerBtnCajas);
        sbCajas.setOnSeekBarChangeListener(listenerSB);
        tvNumCajas = (TextView) findViewById(R.id.textViewCajas);
        tvNumCajas.setText("3");
    }

    private View.OnClickListener listenerBtnCajas= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dibujaCajas(10);
            sbCajas.setProgress(10);
        }
    };

    private SeekBar.OnSeekBarChangeListener listenerSB = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress = (progress <= 1) ? 1 : progress;
            dibujaCajas(progress);
            tvNumCajas.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void dibujaCajas(int numLineas) {
        Bitmap bm = Bitmap.createBitmap(
                contenedor.getWidth(),
                contenedor.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        contenedor.setImageBitmap(bm);
        Paint pen = new Paint();
        int red, green, blue;
        Random ran = new Random();
        red = ran.nextInt(256);
        green = ran.nextInt(256);
        blue =ran.nextInt(256);

        pen.setColor(Color.rgb(red, green, blue));

        int x = contenedor.getWidth() / 2;
        int y = contenedor.getHeight() / 2;
        int ladoChico = contenedor.getWidth() > contenedor.getHeight() ?
                contenedor.getHeight() : contenedor.getWidth();
        ladoChico /= 2;
        canvas.drawLine(x - numLineas, y + numLineas, x + numLineas, y + numLineas, pen);
        canvas.drawLine(x - numLineas, y - numLineas, x + numLineas, y - numLineas, pen);
        canvas.drawLine(x - numLineas, y - numLineas, x - numLineas, y + numLineas, pen);
        canvas.drawLine(x + numLineas, y - numLineas, x + numLineas, y + numLineas, pen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cajas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
