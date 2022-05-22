package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * FileMapper
 *
 * Execute SQL Statement to table FILES in database
 *
 * @author Do Thanh
 */

@Mapper
public interface FileMapper {

    /**
     * Get file by fileName
     *
     * @param: Name of file
     *
     * @return File
     */
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getByFileName(String fileName);

    /**
     * Get the list of file
     *
     * @param: ID of user
     *
     * @return List of File
     */
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getListFile(int userId);

    /**
     * Insert the File to the database
     *
     * @param: File
     *
     * @return true if insert successfully
     */
    @Insert("INSERT INTO FILES " +
            "(filename, contenttype, filesize, userid, filedata) " +
            "VALUES(" +
            "#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    boolean insert(File file);

    /**
     * Delete the File from the database
     *
     * @param: ID of File
     *
     * @return true if delete successfully
     */
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    boolean delete(int fileId);

}
