package cn.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_item_cat")
public class ItemCat extends BasePojo {

    private static final long serialVersionUID = -8545814285854506950L;
    @TableId(type= IdType.AUTO)  //设置主键并且自增
    private Long id;            //商品分类id号
    private Long parentId;      //父级分类Id
    //如果有父子级关系,可以使用parentId进行关联
    private String name;        //名称
    private Integer status;     //商品分类状态
    private Integer sortOrder;  //排序号
    private Boolean isParent;   //是否为父级
    //tinyint 0 false /1 true


    public ItemCat(Long id, Long parentId, String name, Integer status, Integer sortOrder, Boolean isParent) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.isParent = isParent;
    }

    public ItemCat() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
}
