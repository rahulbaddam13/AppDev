package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.net.URL;
import java.util.ArrayList;



public class LinkCollector extends AppCompatActivity implements LinkItemClickListener {
    private RecyclerView linkRV;
    private LinkAdapter linkAdapter;
    private final ArrayList<LinkCollectorItem> links = new ArrayList<>();
    private static final String KEY_OF_LINK = "KEY_OF_LINK";
    private static final String NUMBER_OF_LINKS = "NUMBER_OF_LINKS";
    private FloatingActionButton facButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        init(savedInstanceState);
        facButton = findViewById(R.id.addLinkButton);
        facButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog();
            }
        });
        
        // Reference : https://www.geeksforgeeks.org/swipe-to-delete-and-undo-in-android-recyclerview/
        ItemTouchHelper i = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                LinkCollectorItem deletedItem = links.get(viewHolder.getAdapterPosition());
                Toast.makeText(LinkCollector.this, "Delete link", Toast.LENGTH_SHORT).show();
                int pos = viewHolder.getLayoutPosition();
                links.remove(pos);
                linkAdapter.notifyItemRemoved(pos);
                Snackbar.make(linkRV, "Deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        links.add(pos, deletedItem);
                        linkAdapter.notifyItemInserted(pos);
                    }
                }).show();

            }
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
        });

        i.attachToRecyclerView(linkRV);
    }



    public void popUpDialog() {
        DialogFragment dialog = new LinkCollectorPopUp();
        dialog.show(getSupportFragmentManager(), "LinkDialog");
    }

    @Override
    public void linkClick(String linkURL) {
    //
    }

    //    Reference : https://developer.android.com/reference/java/net/URL
    public static boolean checkURL(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void addLink(DialogFragment linkDialog) {
        Dialog addDialog = linkDialog.getDialog();

        assert addDialog != null;
        String name = ((EditText) addDialog.findViewById(R.id.link_name)).getText().toString();
        String url = ((EditText) addDialog.findViewById(R.id.link_url)).getText().toString();

        if (checkURL(url)) {
            linkDialog.dismiss();
            links.add(0, new LinkCollectorItem(name, url));
            linkAdapter.notifyItemInserted(0);
            Snackbar.make(findViewById(android.R.id.content), "Item Added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Failure", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void cancelLink(DialogFragment linkDialog) {
        linkDialog.dismiss();
    }

    // Reference : https://developer.android.com/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size;
        if(links == null) {
            size = 0;
        }
        else{
            size = links.size();
        }
        outState.putInt(NUMBER_OF_LINKS, size);
        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_LINK + i + "0", links.get(i).getName());
            outState.putString(KEY_OF_LINK + i + "1", links.get(i).getUrl());
        }
        super.onSaveInstanceState(outState);
    }

    // Reference : https://developer.android.com/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle)
    private void onPostItem(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_LINKS)) {
            if (links == null || links.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_LINKS);
                for (int i = 0; i < size; i++) {
                    LinkCollectorItem item = new LinkCollectorItem
                            (savedInstanceState.getString(KEY_OF_LINK + i + "0"),
                            savedInstanceState.getString(KEY_OF_LINK + i + "1"));
                    assert links != null;
                    links.add(item);
                }
            }
        }
    }

    private void initiateRV() {
        RecyclerView.LayoutManager layoutManger = new LinearLayoutManager(this);
        linkRV = findViewById(R.id.link_collector_recycler_view);
        linkRV.setHasFixedSize(true);
        linkAdapter = new LinkAdapter(links);
        LinkItemClickListener linkClickListener = new LinkItemClickListener() {
            @Override
            public void linkClick(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void addLink(DialogFragment linkDialog) {
            //
            }

            @Override
            public void cancelLink(DialogFragment linkDialog) {
            //
            }
        };
        linkAdapter.setOnLinkItemClickListener(linkClickListener);
        linkRV.setAdapter(linkAdapter);
        linkRV.setLayoutManager(layoutManger);
    }

    private void init(Bundle savedInstanceState) {
        onPostItem(savedInstanceState);
        initiateRV();
    }



}