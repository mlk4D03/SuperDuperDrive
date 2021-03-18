package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    public List<File> getAllFiles();

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES(#{filename}),#{contenttype},#{filesize},#{userid},#{filedata}")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    public int insertFile(File file);
}
