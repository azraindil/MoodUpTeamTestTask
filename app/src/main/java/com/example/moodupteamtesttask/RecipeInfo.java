package com.example.moodupteamtesttask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class RecipeInfo extends AsyncTask<Void, Void, Void> {
    String data = "";
    String line = "";
    String fullData;
    String ingredients = "", preparing = "";
    public static String img1, img2;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://moodup.team/test/info.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject json = new JSONObject(data);

            JSONArray ingredientsArray = json.getJSONArray("ingredients");

            for (int i = 0; i < ingredientsArray.length(); i++) {

                ingredients += "-" + ingredientsArray.getString(i) + "\n";
            }

            JSONArray preparingArray = json.getJSONArray("preparing");

            for (int i = 0; i < preparingArray.length(); i++) {

                preparing += i + 1 + ". " + preparingArray.getString(i) + "\n\n";
            }
            fullData = json.getString("title") + ": \n\n" + json.getString("description") + "\n\n\bIngredients: \n\n"
                    + ingredients + "\n\n\bPreparing: \n\n" + preparing + "\n\n\bImages: \n\n";

            JSONArray imgArray = json.getJSONArray("imgs");
            img1 = imgArray.getString(0);
            img2 = imgArray.getString(1);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        SecondFragment.txtRecipe.setText(this.fullData);
        Glide.with(SecondFragment.img2).load(img2).into(SecondFragment.img2);
        Glide.with(SecondFragment.img1).load(img1).into(SecondFragment.img1);
    }
}
