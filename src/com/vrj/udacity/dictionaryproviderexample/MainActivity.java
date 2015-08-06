/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vrj.udacity.dictionaryproviderexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView which will be populated with the Dictionary ContentProvider data.
        ListView listView = (ListView) findViewById(R.id.dictionary_list_view);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        
        final int numberOfRows = 2;
        
        // Array of Strings of headers from table we are using
        String [] headers = new String[numberOfRows];
        headers[0] = cursor.getColumnName(cursor.getColumnIndex(Words.WORD));
        headers[1] = cursor.getColumnName(cursor.getColumnIndex(Words.FREQUENCY));   
        
        // Array of int for id of TextViews for ListView
        int[] views = new int[numberOfRows];
        views[0] = android.R.id.text1;  // This is the TextView in the .two_line_list_item
        views[1] = android.R.id.text2;  // This is the TextView in the .two_line_list_item
        
        SimpleCursorAdapter sca = new SimpleCursorAdapter(
        		// Context
        		this,
        		// TextView type that will populate this listview
        		android.R.layout.two_line_list_item,
        		// The Cursor
        		cursor, 
        		// String[] of the headers from the table that we want to display
        		headers, 
        		// int[] of the TextViews that we want to display the table values in, in order
        		views, 
        		// Flags
        		0);
        
        // Set the CursorAdapter for this ListView
        listView.setAdapter(sca);
        
    }
}
