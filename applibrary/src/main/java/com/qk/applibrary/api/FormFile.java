package com.qk.applibrary.api;

/**
 * Created by Leo on 2/4/2015.
 */
public class FormFile {

    /* 文件路径 */
    private String path;
    /* 文件名称 */
    private String fileName;
    /* 表单字段名称*/
    private String formName;
    /* 内容类型 */
    private String contentType = "application/octet-stream"; //需要查阅相关的资料

    public FormFile(String fileName, String formName, String contentType, String path) {
        this.fileName = fileName;
        this.formName = formName;
        this.path = path;
        if (contentType != null) this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
