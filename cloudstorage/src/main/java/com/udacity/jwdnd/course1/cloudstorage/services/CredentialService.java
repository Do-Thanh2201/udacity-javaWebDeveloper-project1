package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    /** encryptionService */
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {

        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
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
    public List<CredentialDTO> getListCredential(int userId) {

        List<Credential> credentials = credentialMapper.getListCredential(userId);

        return  credentials.stream().map(this::toCredentialDTO).collect(Collectors.toList());
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
     * Update the Credential to the database
     *
     * @param: Credential
     *
     * @return true if insert successfully
     */
    public boolean update(Credential credential) {
        return credentialMapper.update(credential);
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
    public Credential toCredential(String url, String username, String decryptPassword, int userId) {
        Credential credential = new Credential();

        credential.setUrl(url);
        credential.setUsername(username);
        credential.setKey(encryptionService.keyAutoGen());
        credential.setPassword(encryptionService.encryptValue(decryptPassword, credential.getKey()));
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

    /**
     * convert from Credential to CredentialDTO
     *
     * @param: credential
     *
     * @return credentialDTO
     */
    private CredentialDTO toCredentialDTO (Credential credential) {

        CredentialDTO credentialDTO = new CredentialDTO();

        credentialDTO.setCredentialId(credential.getCredentialId());
        credentialDTO.setUrl(credential.getUrl());
        credentialDTO.setUsername(credential.getUsername());
        credentialDTO.setEncryptPassword(credentialDTO.getEncryptPassword());
        credentialDTO.setDecryptPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));

        return credentialDTO;
    }
}
