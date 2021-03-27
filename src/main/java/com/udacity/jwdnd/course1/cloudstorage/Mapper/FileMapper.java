package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Performs multiple queries for the file table.
 */
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    public List<File> getAllFiles();

    @Select("SELECT * FROM FILES where fileId = #{fileid}")
    public File getFile(Integer fileid);

    @Select("SELECT * FROM FILES where filename = #{filename}")
    public File getFileByName(String filename);

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    public int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileid}")
    public int deleteFile(Integer fileid);
}
