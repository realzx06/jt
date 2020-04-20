package cn.jt.vo;

public class ImageVO {
    private Integer error;  //是否上传正确  0正常  1错误
    private String url;     //图片的虚拟访问路径
    private Integer width;  //图片固有属性
    private Integer height; //固有属性

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public ImageVO() {
    }

    public ImageVO(Integer error, String url, Integer width, Integer height) {
        this.error = error;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    //为了调用方便准备工具方法
    public static ImageVO fail() {

        return new ImageVO(1, null, null, null);
    }

    public static ImageVO success(String url,Integer width,Integer height) {

        return new ImageVO(0, url, width, height);
    }

}
