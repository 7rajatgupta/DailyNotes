package com.rgapps.dailynotes.other;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

import com.rgapps.dailynotes.R;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rajat on 7/2/17.
 */

public class Note {
    /**
     * Model layer.
     */
    private UUID mId;
    private String mTitle, mContent;
    private Date mDate;
    private Drawable mThumbnail;
    private boolean isFavorite = false;



    public Note(){

        //Generate a unique identifier.

        this.mId = UUID.randomUUID();
        this.mDate = new Date();
        this.isFavorite = false;
        //this.mThumbnail = ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.new_note_default, null);



    }
    //Getters and setters.

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Drawable getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        mThumbnail = thumbnail;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
