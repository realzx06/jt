package cn.jt.vo;

import cn.jt.pojo.Item;

import java.io.Serializable;
import java.util.List;

/**
 * 表格VO对象
 */
public class EasyUITable  implements Serializable {
    public EasyUITable() {
    }

    public EasyUITable(Long total, List<Item> rows) {
        this.total = total;
        this.rows = rows;
    }

    private Long total;//总条数
    private List<Item> rows;//每页保存的数据

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Item> getRows() {
        return rows;
    }

    public void setRows(List<Item> rows) {
        this.rows = rows;
    }
}
