package com.rgapps.dailynotes.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rgapps.dailynotes.R;
import com.rgapps.dailynotes.other.Note;
import com.rgapps.dailynotes.other.NoteLab;

import java.util.UUID;

public class NoteDetailActivity extends AppCompatActivity {

    /**
     *
     * Created to show the detailed note when the user clicks on the note.
     * @param savedInstanceState
     */
    private Note mNote;
    private Toolbar mToolbar;
    private TextView mTitleTextView, mContentTextView;
    private ImageView mNoteThumbnail;
    private FloatingActionButton mFabEdit;
    public static final String EXTRA_NOTE_ID = "com.rgapps.dailynotes.note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        mToolbar = (Toolbar) findViewById(R.id.note_detail_toolbar);
        mToolbar.setTitle("DailyNotes");
        mToolbar.setSubtitle("View Note");
        mTitleTextView = (TextView) findViewById(R.id.note_detail_title);
        mContentTextView = (TextView) findViewById(R.id.note_detail_content);
        mNoteThumbnail = (ImageView) findViewById(R.id.note_detail_thumbnail);
        mFabEdit = (FloatingActionButton) findViewById(R.id.fab_edit_note);

        //Fetching the note id as the 'extra' in the intent bundle;
        UUID fetchNoteId = (UUID) getIntent().getSerializableExtra(NoteActivity.EXTRA_NOTE_ID);
        mNote = NoteLab.get(getApplicationContext()).getNote(fetchNoteId); //Got that specified note.
        mTitleTextView.setText(mNote.getTitle());
        mContentTextView.setText(mNote.getContent());
        mNoteThumbnail.setImageDrawable(mNote.getThumbnail());

        mFabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit the Note.
                Intent intent = new Intent(getApplicationContext(),NoteCreationActivity.class);
                intent.putExtra(EXTRA_NOTE_ID, mNote.getId());
                startActivity(intent);

            }
        });
        


    }
}
