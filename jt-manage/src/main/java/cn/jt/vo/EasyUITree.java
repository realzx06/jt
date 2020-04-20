package cn.jt.vo;

import cn.jt.pojo.Item;

import java.io.Serializable;
import java.util.List;

/**
 * 树型结构VO对象
 */
public class EasyUITree implements Serializable {

    private Long id; //节点ID信息
    private String text;//节点名称
    private String state;//节点状态

    public EasyUITree(Long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public EasyUITree() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
