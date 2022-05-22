package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Credential
 *
 * Hold information of table CREDENTIALS in database
 *
 * @author Do Thanh
 */
@Getter
@Setter
public class Credential {

    /** credentialId: Map to credentialid of table Credential in database */
    private int credentialId;

    /** url: Map to url of table Credential in database */
    private String url;

    /**  username: Map to username of table Credential in database */
    private String username;

    /**  key: Map to key of table Credential in database */
    private String key;

    /** password :Map to password of table Credential in database */
    private String password;

    /** userId: Map to userid of table Credential in database */
    private int userId;
}
