package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("Select * From CREDENTIALS WHERE credentialid = #{credentialid}")
    Credentials getCredential(int credentialid);

    @Select("Select * From CREDENTIALS")
    List<Credentials> getCredentials();

    @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid) VALUES(#{url}, #{username}, #{password}, #{key}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredentials (Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int deleteCredential(int credentialid);

    @Update("UPDATE CREDENTIALS SET url=#{url}, key=#{key}, password =#{password} WHERE credentialid=#{credentialid}")
    int updateCredential(Credentials credentials);
}
