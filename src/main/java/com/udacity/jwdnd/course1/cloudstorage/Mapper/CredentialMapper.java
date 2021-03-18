package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    public List<Credential> getAllCredentials();

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUE(#{url},#{username},#{key},#{password},#{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "credentialid")
    public int insertCredential(Credential credential);
}
