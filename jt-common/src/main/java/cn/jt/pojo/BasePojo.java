package cn.jt.pojo;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

//pojo基类，完成2个任务，2个日期，实现序列化
public class BasePojo implements Serializable{
    private static final long serialVersionUID = -3121669440061630976L;
    private Date created;
    private Date updated;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
