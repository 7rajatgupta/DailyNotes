package com.rgapps.dailynotes.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.rgapps.dailynotes.R;
import com.rgapps.dailynotes.fragment.NoteFragment;
import com.rgapps.dailynotes.other.Note;
import com.rgapps.dailynotes.other.NoteAdapter;
import com.rgapps.dailynotes.other.NoteLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by rajat on 9/2/17.
 */

public class NoteCreationActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private FloatingActionButton mFabSave;
    private EditText mNewNoteTitle, mNewNoteContent;
    private ImageView mNewNoteThumbnail;
    private Note mNote;
    private String mTempTitle, mTempContent;
    private NoteFragment mNoteFragment;
    private Handler mdelayHandler;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);
        mToolbar = (Toolbar)findViewById(R.id.new_note_toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setSubtitle("Create a new Note!");


        //Checking to see whether a existing note is being edited or what
        UUID fetchNoteId = (UUID) getIntent().getSerializableExtra(NoteActivity.EXTRA_NOTE_ID);
        mNote = NoteLab.get(getApplicationContext()).getNote(fetchNoteId); //Got that specified note.

        //additional code

        mNoteFragment = new NoteFragment();
        mFabSave = (FloatingActionButton) findViewById(R.id.fab_save_note);
        mNewNoteTitle = (EditText)findViewById(R.id.new_note_title);
        mNewNoteContent = (EditText)findViewById(R.id.new_note_content);
        mNewNoteThumbnail = (ImageView) findViewById(R.id.new_note_thumbnail);
        if (mNote != null){
            //Open that existing note.
            mToolbar.setSubtitle("Edit Note");
            mNewNoteTitle.setText(mNote.getTitle());
            mNewNoteContent.setText(mNote.getContent());
            mNewNoteThumbnail.setImageDrawable(mNote.getThumbnail());

        }
        mNewNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //What do you wanna do man !
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTempTitle = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mNewNoteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTempContent = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mNoteFragment.updateNote(mTempTitle,mTempContent, null);
                Snackbar.make(v, "Saved", Snackbar.LENGTH_SHORT).setAction("GOT IT", null).show();
                // TODO: Refresh the data set & display.


                mdelayHandler = new Handler();
                mdelayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), NoteActivity.class));

                    }
                }, 1500);


            }

        });



    }

}
