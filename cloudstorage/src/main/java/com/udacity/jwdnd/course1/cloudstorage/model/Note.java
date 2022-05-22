package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Note
 *
 * Hold information of table NOTES in database
 *
 * @author Do Thanh
 */
@Getter
@Setter
public class Note {

    /** noteId: map to fileId of table Note in database */
    private int noteId;

    /** noteTitle: map to notetitle of table Note in database */
    private String noteTitle;

    /** noteDescription: map to notedescription of table Note in database */
    private String noteDescription;

    /** userId: map to userid of table Note in database */
    private int userId;


}
