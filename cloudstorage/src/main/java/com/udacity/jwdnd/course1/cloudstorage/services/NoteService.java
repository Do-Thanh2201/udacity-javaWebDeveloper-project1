package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NoteService
 *
 * @author Do Thanh
 */
@Service
public class NoteService {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========

    /** noteMapper*/
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========


    /**
     * Get Note by noteId
     *
     * @param: ID of table NOTES
     * @return Note
     */
    public Note getByNoteId(String noteId) {
        return noteMapper.getByNoteId(noteId);
    };

    /**
     * Get the list of Note by userId
     *
     * @param: ID of user
     *
     * @return List of Note
     */
    public List<Note> getListNote(int userId) {
        return noteMapper.getListNote(userId);
    };

    /**
     * Insert the Note to the database
     *
     * @param: Note
     *
     * @return true if insert successfully
     */
    public boolean insert(Note note) {
        return noteMapper.insert(note);
    };

    /**
     * Edit the Note to the database
     *
     * @param: Note
     *
     * @return true if insert successfully
     */
    public boolean edit(Note note) {
        return noteMapper.update(note);
    };


    /**
     * Set value for Note
     *
     * @param: Title of Note
     * @param: Description of Note
     * @param: ID of user
     *
     * @return Note
     */
    public Note setToNote(String noteTitle, String noteDescription, int userId) {

        Note note = new Note();

        note.setNoteTitle(noteTitle);
        note.setNoteDescription(noteDescription);
        note.setUserId(userId);

        return note;

    }

    /**
     * Delete the Note from the database
     *
     * @param: ID of Note
     *
     * @return true if delete successfully
     */
    public boolean delete(int noteId) {
        return noteMapper.delete(noteId);
    };

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

}
