package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * FileController
 *
 * @author Do Thanh
 */
@Controller
@RequestMapping("/file")
public class FileController {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** Empty Uploaded file message */
    private static final String EXIST_FILE_MESSAGE             = "File is exist: ";

    /** Upload file successfully message */
    private static final String UPLOAD_FILE_SUCCESS_MESSAGE    = "You successfully upload file: ";

    /** Delete file successfully message */
    private static final String DELETE_FILE_SUCCESS_MESSAGE    = "You successfully delete file!";

    /** Empty Uploaded file message */
    private static final String EMPTY_FILE_MESSAGE             = "Please select a file to upload.";

    /** Upload file error message */
    private static final String DELETE_FILE_ERROR_MESSAGE      = "An error occurred while deleting the file!";

    /** Upload file error message */
    private static final String UPLOAD_FILE_ERROR_MESSAGE      = "An error occurred while uploading the file: ";

    //=====================================================
    //                                                   DI
    //                                             ========

    /** fileService*/
    private FileService fileService;

    /** userService */
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }
    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen view the list file
     *
     * @return Screen Homepage
     */
    @GetMapping
    public String getFile() {
        return "redirect:/home";
    }

    /**
     * Screen Add new the file
     *
     * @return Screen Homepage
     */
    @PostMapping()
    public String addFile(@RequestParam("fileUpload") MultipartFile fileUpload, RedirectAttributes redirectAttrs) {

        // Check if file is empty
        if (fileUpload.isEmpty()) {

            redirectAttrs.addFlashAttribute("message", EMPTY_FILE_MESSAGE);
            return "redirect:/home";
        }

        // Get file name
        String fileName = StringUtils.cleanPath(fileUpload.getOriginalFilename());

        // Check file exist in the database
        if (fileService.getByFilename(fileName) != null) {

            redirectAttrs.addFlashAttribute("message", EXIST_FILE_MESSAGE + fileName + '!');
            return "redirect:/home";
        }

        Integer userId;
        try {
            userId = userService.getUserID();
        }
        catch (Exception e) {
            redirectAttrs.addFlashAttribute("message", UserService.NOT_EXIST_USER_MESSAGE);
            return "redirect:/login";
        }

        // Save the file on the database
        try {

            File file = fileService.setToFile(
                    fileName
                    , fileUpload.getContentType()
                    , String.valueOf(fileUpload.getSize())
                    , userId
                    , fileUpload.getBytes()
            );
            fileService.insert(file);

            // return success response
            redirectAttrs.addFlashAttribute("message", UPLOAD_FILE_SUCCESS_MESSAGE + fileName + '!');

        } catch (IOException e) {
            e.printStackTrace();

            // return error response
            redirectAttrs.addFlashAttribute("fileMessage", UPLOAD_FILE_ERROR_MESSAGE + fileName + '!');
        }

        // Redirect to Homepage
        return "redirect:/home";
    }

    /**
     * Screen delete the file
     *
     * @return Screen Homepage
     */
    @GetMapping("/delete")
    public String deleteFile(@RequestParam("fileId") int fileId, RedirectAttributes redirectAttrs) {

        if (!fileService.delete(fileId)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", DELETE_FILE_ERROR_MESSAGE);
            return "redirect:/home";
        };

        // return success response
        redirectAttrs.addFlashAttribute("message", DELETE_FILE_SUCCESS_MESSAGE);

        return "redirect:/home";
    }
    @GetMapping("/view")
    public ResponseEntity viewFile(@RequestParam("fileId") int fileId) {
        File file = fileService.getByFileId(fileId);
        String filename = file.getFileName();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                /*replace "attachment" with "inline" if you want another browser tab to be opened to view file
                instead of directly downloading files.*/
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
                .body(file.getFileData());
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

}
