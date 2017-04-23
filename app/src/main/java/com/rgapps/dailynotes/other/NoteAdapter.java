package com.rgapps.dailynotes.other;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rgapps.dailynotes.R;
import com.rgapps.dailynotes.activity.NoteActivity;

import java.util.List;


/**
 * Created by rajat on 9/2/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    /**
     * Adapter for the Recycler View.
     */
    private Context mContext;
    private List<Note> mNoteList;



    //The ViewHolder implementation.
    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle, mDate_created;
        private ImageView mThumbnail, mOverflow;
        private Note mNote;

        public  NoteViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            mTitle = (TextView) view.findViewById(R.id.note_title);
            mDate_created = (TextView) view.findViewById(R.id.date_created);
            mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            mOverflow = (ImageView) view.findViewById(R.id.overflow);
        }
        @Override
        public void onClick(View view){
            Intent intent = NoteActivity.newIntent(mContext,mNote.getId());
            mContext.startActivity(intent);



        }
        public void bindNote(Note note){
            mNote = note;
            mTitle.setText(note.getTitle());
            mThumbnail.setImageDrawable(note.getThumbnail());
            mDate_created.setText(note.getDate().toString());

        }
    }
    public void updateUI(){
        //Invoke the method to update the UI from the adapter layer.
    }

    public NoteAdapter(Context context, List<Note> noteList){
        this.mContext = context;
        this.mNoteList = noteList;

    }
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        return new NoteViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position){
        Note note = mNoteList.get(position);
        holder.bindNote(note); //calling the method defined in the view holder class


        try {
            Glide.with(mContext).load(note.getThumbnail()).into(holder.mThumbnail);

        }catch (Exception e){
            e.printStackTrace();

        }
        holder.mOverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(holder.mOverflow, position);
            }
        });
    }
    @Override
    public int getItemCount(){
        return mNoteList.size();
    }

    //Show PopUp menu:
    public void showPopUpMenu(View view, int position){
        //Inflate the menu:
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_note, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new NoteMenuItemClickListener(position));
        popupMenu.show();

    }
    public class NoteMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        private int mPosition;

        public NoteMenuItemClickListener(int position){
            mPosition = position;
        }
        @Override
        public boolean onMenuItemClick(MenuItem item){
            Note note = mNoteList.get(mPosition);
            switch (item.getItemId()){
                case R.id.menu_action_add_favourite:
                    note.setFavorite(true);
                    Toast.makeText(mContext, "Added to favorites", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_action_delete:
                    mNoteList.remove(mPosition);
                    notifyDataSetChanged();
                    return true;
                default:

            }
            return false;

        }
    }




}
