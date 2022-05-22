package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

/**
 * File
 *
 * Hold information of table FILES in database
 *
 * @author Do Thanh
 */

@Getter
@Setter
public class File {

    /** fileId: Map to fileId of table File in database */
    private int fileId;

    /** fileName: Map to filename of table File in database */
    private String fileName;

    /** contentType: Map to contentType of table File in database */
    private String contentType;

    /** fileSize: Map to fileSize of table File in database */
    private String fileSize;

    /** userId: Map to userId of table File in database */
    private int userId;

    /** fileData: Map to fileData of table File in database */
    private byte[] fileData;

}
