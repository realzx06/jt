package cn.jt.controller;

import cn.jt.service.FileService;
import cn.jt.vo.ImageVO;
import cn.jt.pojo.Item;
import cn.jt.pojo.ItemDesc;
import cn.jt.vo.EasyUITable;
import cn.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("item")
public class ItemController {

	@Autowired
	private FileService fileService;

	@Autowired
	private ItemService itemService;

	//分页查询商品信息
	@RequestMapping("query")
	public EasyUITable query(int page,int rows){
		EasyUITable itemByPage = itemService.findItemByPage(page, rows);
		//System.out.println(itemByPage.getTotal());

		return	itemByPage;
	}

	//新增商品
	@RequestMapping("save")
	public SysResult save(Item item, ItemDesc itemDesc){
		itemService.saveItem(item,itemDesc);
		return  SysResult.success();
	}
	//商品修改接口

	@RequestMapping("update")
	public SysResult updateItem(Item item){
		System.out.println("1111");
		itemService.updateItem(item);
		return SysResult.success();

	}

	//删除商品接口
	@RequestMapping("delete")
	public SysResult deleteItem(Long[] ids){
		itemService.deleteItem(ids);
		return SysResult.success();
	}

	//商品详情数据回显
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {

		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		return SysResult.success(itemDesc);
	}




	
	
}
