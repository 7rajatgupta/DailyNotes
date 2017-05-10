package com.rgapps.dailynotes.other;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.rgapps.dailynotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rajat on 10/2/17.
 */

public class NoteLab {
    //A singleton class for storing the notes.
    private static NoteLab sNoteLab;
    private List<Note> mNotes;
    private Drawable mThumbnail;


    public static NoteLab get(Context context){

        if(sNoteLab == null)
            sNoteLab = new NoteLab(context);
        return sNoteLab;
    }
    public void addNote(Note note){
        mNotes.add(note);

    }

    private NoteLab(Context context){
        mNotes = new ArrayList<>();


    }
    public List<Note> getNotes(){
        return mNotes;
    }
    public Note getNote(UUID uuid){
        for (Note note : mNotes){
            if (note.getId().equals(uuid)){
                return note;
            }
        }
        return null;
    }
}
