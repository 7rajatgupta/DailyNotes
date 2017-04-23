package com.rgapps.dailynotes.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rgapps.dailynotes.R;
import com.rgapps.dailynotes.fragment.NoteCreationFragment;
import com.rgapps.dailynotes.fragment.NoteFragment;
import com.rgapps.dailynotes.other.Note;
import com.rgapps.dailynotes.other.NoteLab;

import java.util.UUID;

public class NoteActivity extends SingleFragmentActivity {

    public static final String EXTRA_NOTE_ID = "com.rgapps.dailynotes.note_id";

    public static Intent newIntent(Context packageContext, UUID noteId){
        Intent intent = new Intent(packageContext,NoteDetailActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return  new NoteFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.add_new_note:
                Note note = new Note();
                NoteLab.get(this).addNote(note);
                Intent i = new Intent(getApplicationContext(), NoteCreationActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }



}
