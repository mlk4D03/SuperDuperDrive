package com.udacity.jwdnd.course1.cloudstorage.Model;

import java.sql.Blob;

public class File {

    private Integer fileId;

    private String filename;

    private String contenttype;

    private String filesize;

    private Integer userid;

    private byte[] filedata;

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }
}
