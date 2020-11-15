package com.example.moodupteamtesttask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SaveImg {

    void saveImgMethod(String imageURL, Context context, String fileName, Context context2){

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + (R.string.app_name) + "/";

        final File dir = new File(dirPath);

        Glide.with(context)
                .load(imageURL)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                        Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                        saveImage(bitmap, dir, fileName, context2);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);

                    }
                });

    }
    private void saveImage(Bitmap image, File storageDir, String imageFileName, Context context) {

        boolean successDirCreated = false;
        if (!storageDir.exists()) {
            successDirCreated = storageDir.mkdir();
        }
        if (successDirCreated) {
            File imageFile = new File(storageDir, imageFileName);
            String savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
                Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Error while saving image!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
        }
    }
}
