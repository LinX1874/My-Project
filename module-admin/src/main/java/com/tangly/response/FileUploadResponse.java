package com.tangly.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * date: 2018/5/16 14:26 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
@ApiModel(description = "文件上传结果返回对象")
public class FileUploadResponse {

    /**
     * 文件名所使用的uuid
     */
    private String uuid;
    /**
     * 使用的附件路径
     */
    private String path;
    /**
     * 文件后缀名
     */
    private String ext;
    /**
     * 文件保存后的相对路径
     */
    @ApiModelProperty(value = "文件存储后的相对路径")
    private String url;
    /**
     * 缩略图路径
     */
    @ApiModelProperty(value = "缩略图存储后的相对路径（如果是图片的话）")
    private String sUrl;

    public FileUploadResponse(String uuid, String path, String ext, String url, String sUrl) {
        this.uuid = uuid;
        this.path = path;
        this.ext = ext;
        this.url = url;
        this.sUrl = sUrl;
    }
}
