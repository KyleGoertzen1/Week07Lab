package domainmodel;

import java.io.Serializable;
import java.sql.Date;

public class Note implements Serializable {

    private int noteId;
    private Date dateCreated;
    private String contents;

    public Note() {

    }

    public Note(int noteId) {
        this.noteId = noteId;
    }

    public Note(int noteId, String contents) {
        this.noteId = noteId;
        this.contents = contents;
    }

    public Note(int noteId, Date dateCreated, String contents) {
        this.noteId = noteId;
        this.dateCreated = dateCreated;
        this.contents = contents;
    }

    public int getNoteId() {
        return noteId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getContents() {
        return contents;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
