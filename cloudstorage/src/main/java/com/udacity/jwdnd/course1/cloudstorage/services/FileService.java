package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileService
 *
 * @author Do Thanh
 */
@Service
public class FileService {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========

    /** fileMapper*/
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Get file by fileName
     *
     * @param: name of file
     *
     * @return File
     */
    public File getByFilename(String fileName) {
        return fileMapper.getByFileName(fileName);
    };

    /**
     * Get the list of file
     *
     * @param: ID of user
     *
     * @return List of File
     */
    public List<File> getListFile(int userId) {
        return fileMapper.getListFile(userId);
    };

    /**
     * Insert the File to the database
     *
     * @param: file insert
     *
     * @return true if insert successfull
     */
    public boolean insert(File file) {
        return fileMapper.insert(file);
    };

    /**
     * Set value for File
     *
     * @param: Name of File
     * @param: ContentType
     * @param: Size of File
     * @param: ID of user
     * @param: Data of File
     *
     * @return File
     */
    public File setToFile(String fileName, String contentType, String fileSize, int userId, byte[] fileData) {
        File file = new File();

        file.setFileName(fileName);
        file.setContentType(contentType);
        file.setFileSize(fileSize);
        file.setUserId(userId);
        file.setFileData(fileData);

        return file;
    }

    /**
     * Delete the File from the database
     *
     * @param: ID of file
     *
     * @return true if delete successfull
     */
    public boolean delete(int fileId) {
        return fileMapper.delete(fileId);
    };

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

}
