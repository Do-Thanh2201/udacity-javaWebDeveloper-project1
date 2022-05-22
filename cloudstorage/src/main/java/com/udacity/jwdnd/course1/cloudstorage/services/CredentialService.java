package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CredentialService
 *
 * @author Do Thanh
 */
@Service
public class CredentialService {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========

    /** credentialMapper */
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========


    /**
     * Get Credential by credentialId
     *
     * @param: id of table CREDENTIALS
     *
     * @return Credential
     */
    public Credential getByCredentialId(String credentialId) {
        return credentialMapper.getByCredentialId(credentialId);
    };

    /**
     * Get the list of Credential
     *
     * @param: ID of user
     *
     * @return List of Credential
     */
    public List<Credential> getListCredential(int userId) {
        return credentialMapper.getListCredential(userId);
    };

    /**
     * Insert the Credential to the database
     *
     * @param: Credential
     *
     * @return true if insert successfully
     */
    public boolean insert(Credential credential) {
        return credentialMapper.insert(credential);
    };

    /**
     * Set value for Credential
     *
     * @param: url
     * @param: username
     * @param: key
     * @param: password
     * @param: ID of user
     *
     * @return Credential
     */
    public Credential setToCredential(String url, String username, String key, String password, int userId) {
        Credential credential = new Credential();

        credential.setUrl(url);
        credential.setUsername(username);
        credential.setKey(key);
        credential.setPassword(password);
        credential.setUserId(userId);

        return credential;
    }

    /**
     * Delete the Credential from the database
     *
     * @param: ID of Credential
     *
     * @return true if delete successfully
     */
    public boolean delete(int credentialId) {
        return credentialMapper.delete(credentialId);
    };

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

}
