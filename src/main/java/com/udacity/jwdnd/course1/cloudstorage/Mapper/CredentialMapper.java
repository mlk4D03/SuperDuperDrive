package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    public List<Credential> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public Credential getCredential(Integer credentialid);

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES(#{url},#{username},#{key},#{password},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "credentialid")
    public int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, password = #{password}, username = #{username} WHERE credentialid = #{credentialid}")
    public int updateCredentials(String url, String password,String username, Integer credentialid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public int deleteCredential(Integer credentialid);
}
