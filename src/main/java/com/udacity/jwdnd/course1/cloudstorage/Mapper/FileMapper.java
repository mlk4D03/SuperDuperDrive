package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    public List<File> getAllFiles();

    @Select("SELECT * FROM FILES where filename = #{filename}")
    public File getFile(String filename);


    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    public int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileid}")
    public int deleteFile(Integer fileid);
}
