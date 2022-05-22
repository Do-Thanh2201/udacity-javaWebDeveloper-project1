package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CredentialMapper
 *
 * Execute SQL Statement to table CREDENTIALS in database
 *
 * @author Do Thanh
 */
@Mapper
public interface CredentialMapper {

    /**
     * Get Credential by credentialId
     *
     * @param: id of table CREDENTIALS
     *
     * @return Credential
     */
    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getByCredentialId(String credentialId);

    /**
     * Get the list of Credential
     *
     * @param: ID of user
     *
     * @return List of Credential
     */
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getListCredential(int userId);

    /**
     * Insert the Credential to the database
     *
     * @param: Credential
     *
     * @return true if insert successfully
     */
    @Insert("INSERT INTO CREDENTIALS " +
            "(url, username, key, password, userid) " +
            "VALUES(" +
            "#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    boolean insert(Credential credential);

    /**
     * Delete the Credential from the database
     *
     * @param: ID of Credential
     *
     * @return true if delete successfully
     */
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    boolean delete(int credentialId);
}
