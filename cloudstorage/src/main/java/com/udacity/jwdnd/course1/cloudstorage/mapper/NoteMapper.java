package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * NoteMapper
 *
 * Execute SQL Statement to table NOTES in database
 *
 * @author Do Thanh
 */
@Mapper
public interface NoteMapper {

    /**
     * Get Note by noteId
     *
     * @param: ID of table NOTES
     * @return Note
     */
    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getByNoteId(String noteId);

    /**
     * Get the list of Note by userId
     *
     * @param: ID of user
     *
     * @return List of Note
     */
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getListNote(int userId);

    /**
     * Insert the Note to the database
     *
     * @param: Note
     *
     * @return true if insert successfully
     */
    @Insert("INSERT INTO NOTES " +
            "(notetitle, notedescription, userid) " +
            "VALUES(" +
            "#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    boolean insert(Note note);

    /**
     * Delete the Note from the database
     *
     * @param: ID of Note
     *
     * @return true if delete successfully
     */

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    boolean delete(int noteId);
}
