package com.udacity.jwdnd.course1.cloudstorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {
    /** noteId: map to fileId of table Note in database */
    private int noteId;

    /** noteTitle: map to notetitle of table Note in database */
    private String noteTitle;

    /** noteDescription: map to notedescription of table Note in database */
    private String noteDescription;
}
