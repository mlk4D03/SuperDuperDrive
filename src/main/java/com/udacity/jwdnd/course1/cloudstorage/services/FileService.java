package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    public int insertFile(MultipartFile file,Integer userid) throws IOException {
        return this.fileMapper.insertFile(new File(null,file.getOriginalFilename(),file.getContentType(),String.valueOf(file.getSize()),userid,file.getBytes()));
    }

    public int deleteFile(Integer fileid){
        return this.fileMapper.deleteFile(fileid);
    }

    public List<File> getAllFiles(){
        return this.fileMapper.getAllFiles();
    }

    public File getFile(Integer fileid){
        return this.fileMapper.getFile(fileid);
    }

    public boolean isFilenameInUse(String filename){
        return this.fileMapper.getFileByName(filename) != null;
    }
}
