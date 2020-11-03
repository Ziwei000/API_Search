package com.mobile_computing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultDisplayActivity extends AppCompatActivity {
    public void saveBitmap(View view, String name) {
        String fileName = name + ".png";
        Bitmap bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bm);
        view.draw(canvas);
        //Toast.makeText(ResultDisplayActivity.this, "save", Toast.LENGTH_LONG).show();
        String TAG = "ACTIVITYLOG";
        Log.e(TAG, "SAVING IMAGE");
        //String s = Environment.getExternalStorageDirectory().getPath();
        //Toast.makeText(ResultDisplayActivity.this, s, Toast.LENGTH_LONG).show();

        File f = new File("/sdcard/favorites/", fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i(TAG, "IMAGE SAVED");
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoader imageloader = VolleySingleton.getInstance(this).getImageLoader();
        setContentView(R.layout.result);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String imageurl0 = bundle.getString("imageurl0");
        /*String[] urlArray = imageurl0.split(":");
        String p = "https:";
        String imageurl_s = p.concat(urlArray[1]);*/

        final String title0 = bundle.getString("title0");
        String date0 = bundle.getString("date0");
        String text0 = bundle.getString("text0");

        NetworkImageView t_iimage = (NetworkImageView) findViewById(R.id.iimg);
        TextView t_title0 = (TextView) findViewById(R.id.tv_title);
        TextView t_date0 = (TextView) findViewById(R.id.tv_date);
        TextView t_text0 = (TextView) findViewById(R.id.tv_text);

        t_title0.setText(title0);
        t_date0.setText(date0);
        t_text0.setText(text0);

        //String url = "https://stephenking.com/images/limited_edition/salems-lot/salems_lot_dse_prop_embed.png";
        t_iimage.setImageUrl(imageurl0, imageloader);

        Button starButton = (Button) findViewById(R.id.starButton);

        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view.draw(Canvas canvas);
                //RelativeLayout total = (RelativeLayout) findViewById(R.id.total);
                //LinearLayout ttotal = (LinearLayout) findViewById(R.id.ttotal);
                NetworkImageView img0 = (NetworkImageView) findViewById(R.id.iimg);
                TextView text1 = (TextView) findViewById(R.id.tv_text) ;
                String save_img = title0 + "_img";
                String save_txt = title0 + "_txt";
                saveBitmap(img0, save_img);
                saveBitmap(text1, save_txt);
                Toast.makeText(ResultDisplayActivity.this, "added to favorites", Toast.LENGTH_LONG).show();
            }
        });

      /*  RequestQueue requestQueue = Volley.newRequestQueue(ResultDisplayActivity.this);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                t_iimage.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });*/

        Button back_button = (Button) findViewById(R.id.back);

        //back to previous activity
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}