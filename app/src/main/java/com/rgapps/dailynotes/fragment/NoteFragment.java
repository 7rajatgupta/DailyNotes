package com.rgapps.dailynotes.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rgapps.dailynotes.R;
import com.rgapps.dailynotes.activity.NoteActivity;
import com.rgapps.dailynotes.other.Note;
import com.rgapps.dailynotes.other.NoteAdapter;
import com.rgapps.dailynotes.other.NoteLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat on 8/2/17.
 */

public class NoteFragment extends Fragment {


    /**
     * Controller class for the fragment fragment_note.xml
     */
    private Note mNote;
    private TextView mNoteTitleField;
    private RecyclerView mRecyclerView;
    private List<Note> mNoteList;
    private Drawable mThumbnail;
    private NoteAdapter mNoteAdapter;






    //Override methods here..
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Additional code here.


        mNoteList = new ArrayList<>();
        //mNoteAdapter = new NoteAdapter(getActivity(), mNoteList);
        //Initialize the collapsing toolbar here

        //updateNote();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View thisView = inflater.inflate(R.layout.fragment_note, container, false);
        //Wiring up the widgets in the layout:



        //Working
        mNoteTitleField = (TextView) thisView.findViewById(R.id.note_title);

        mRecyclerView = (RecyclerView) thisView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.setAdapter(mNoteAdapter);

        updateUI();



        //prepareNotes

        return  thisView;

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int mSpanCount, mSpacing;
        private  boolean mIncludeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
            this.mSpanCount = spanCount;
            this.mSpacing = spacing;
            this.mIncludeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            int position = parent.getChildAdapterPosition(view);
            int column = position  % mSpanCount;

            if (mIncludeEdge){
                outRect.left = mSpacing - column * mSpacing / mSpanCount;
                outRect.right = (column + 1) * mSpacing / mSpanCount;

                if (position < mSpanCount){
                    //top edge
                    outRect.top = mSpacing;
                }
                outRect.bottom = mSpacing; //item bottom;
            }
            else {
                outRect.left = column * mSpacing / mSpanCount;
                outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;

                if (position >= mSpanCount){
                    outRect.top = mSpacing; //Item top
                }

            }
        }
    }
    /*private void updateNote(){

        mThumbnail = ResourcesCompat.getDrawable(getResources(),R.drawable.new_note_default, null );


        Note note = new Note("New Note", "This is the content", mThumbnail);

        mNoteList.add(note);
        Note note1 = new Note("another one", "This is the content", mThumbnail);
        mNoteList.add(note1);
        mNoteAdapter.notifyDataSetChanged(); // Refresh Adapter, see what's been changed
    }



    public void updateNote(String title, String content, Drawable thumbnail){
         Drawable thumbnailIsNull;
        thumbnailIsNull = ResourcesCompat.getDrawable(getResources(), R.drawable.new_note_default, null);

        if (thumbnail == null){
            Note newNote = new Note(title, content, thumbnailIsNull);
            mNoteList.add(newNote);
            mNoteAdapter.notifyDataSetChanged();

        }


    }
    */
    private int dpToPx(int dp){
        //Converting dp's to pixels:
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
    public void updateUI(){
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notesList = noteLab.getNotes();
        mNoteAdapter = new NoteAdapter(getActivity(), notesList);
        mRecyclerView.setAdapter(mNoteAdapter);
    }




}
