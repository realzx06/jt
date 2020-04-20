package cn.jt.service;

import cn.jt.pojo.Item;
import cn.jt.pojo.ItemDesc;
import cn.jt.vo.EasyUITable;

public interface ItemService {

    EasyUITable findItemByPage(int page, int rows);

    void saveItem(Item item, ItemDesc itemDesc);

    void updateItem(Item item);

    void deleteItem(Long[] ids);

    ItemDesc findItemDescById(Long itemId);
}
