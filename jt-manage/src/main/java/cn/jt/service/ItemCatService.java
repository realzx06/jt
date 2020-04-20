package cn.jt.service;

import cn.jt.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {

    String findItemCatNameById(int itemCatId);

    List<EasyUITree> findItemCatListById(int parentId);

    List<EasyUITree> findItemCatCache(Integer parentId);
}
