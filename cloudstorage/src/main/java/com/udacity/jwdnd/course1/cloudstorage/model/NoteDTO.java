package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {

    /** noteTitle: map to notetitle of table Note in database */
    private String noteTitle;

    /** noteDescription: map to notedescription of table Note in database */
    private String noteDescription;
}
