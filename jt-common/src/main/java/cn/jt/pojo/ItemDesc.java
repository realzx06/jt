package cn.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/*
商品描述
 */
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{
    @TableId
    private Long itemId;//主键信息
    private String  itemDesc;//商品详情

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
