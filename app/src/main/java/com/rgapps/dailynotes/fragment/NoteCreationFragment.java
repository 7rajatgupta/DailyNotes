package com.rgapps.dailynotes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.rgapps.dailynotes.R;

/**
 * Created by rajat on 9/2/17.
 */

public class NoteCreationFragment extends Fragment {

    /**
     * This class is the Controller class for the fragment of note creation.
     */

    private EditText mTitle, mContent;
    private ImageView mThumbnail;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Additional code:
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView = inflater.inflate(R.layout.activity_note_creation, container, false);
        //Wire up the widgets here:
        mTitle = (EditText)thisView.findViewById(R.id.new_note_title);
        mContent = (EditText)thisView.findViewById(R.id.new_note_content);
        mThumbnail = (ImageView)thisView.findViewById(R.id.new_note_thumbnail);


        return  thisView;
    }


}
