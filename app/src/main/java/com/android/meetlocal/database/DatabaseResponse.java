package com.android.meetlocal.database;

import com.google.firebase.database.DatabaseError;

/**
 * Created by hp pc on 13-11-2017.
 */

public class DatabaseResponse {
    private int id;
    private Message message;
    private DatabaseError databaseError;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public DatabaseError getDatabaseError() {
        return databaseError;
    }

    public void setDatabaseError(DatabaseError databaseError) {
        this.databaseError = databaseError;
    }


}
