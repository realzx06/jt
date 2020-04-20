package cn.jt.controller;

import cn.jt.pojo.Item;
import cn.jt.pojo.ItemCat;
import cn.jt.service.ItemCatService;
import cn.jt.service.ItemService;
import cn.jt.vo.EasyUITable;
import cn.jt.vo.EasyUITree;
import cn.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	//回显商品分类信息
@RequestMapping("queryItemName")
	public String queryItemName(int itemCatId){
	//System.out.println(itemCatId);
	String itemCatNameById = itemCatService.findItemCatNameById(itemCatId);
	//System.out.println(itemCatNameById);
	return itemCatNameById;
	}

	//查询商品分类信息
	@RequestMapping("list")							//如果没ID则默认查询父节点
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id",defaultValue = "0")Integer parentId){
		//int parentId=0;
		//从数据库查询商品分类信息
		return itemCatService.findItemCatListById(parentId);
		//c从缓存查询数据
		//return itemCatService.findItemCatCache(parentId);
	}


	
	
}
