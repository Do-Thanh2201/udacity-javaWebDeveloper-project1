package com.udacity.jwdnd.course1.cloudstorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialDTO {

    /** credentialId: Map to credentialid of table Credential in database */
    private int credentialId;

    /** url: Map to url of table Credential in database */
    private String url;

    /**  username: Map to username of table Credential in database */
    private String username;

    /** password (encode) :Map to password of table Credential in database */
    private String encryptPassword;

    /** password (Decode) :Decrypt the password of table Credential in database */
    private String decryptPassword;
}
