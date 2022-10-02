package com.example.numad22fa_rahulreddybaddam;

import androidx.fragment.app.DialogFragment;

public interface LinkItemClickListener {
    void linkClick(String linkURL);
    void addLink(DialogFragment linkDialog);
    void cancelLink(DialogFragment linkDialog);
}