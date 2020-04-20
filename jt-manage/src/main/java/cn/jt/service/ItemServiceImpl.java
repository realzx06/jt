package cn.jt.service;

import cn.jt.mapper.ItemDescMapper;
import cn.jt.pojo.Item;
import cn.jt.pojo.ItemDesc;
import cn.jt.vo.EasyUITable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jt.mapper.ItemMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {




	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;


	@Override
	public EasyUITable findItemByPage(int page, int rows) {
//		//获取总记录数
//		long total = itemMapper.selectCount(null);
//		System.out.println(total);
//		//计算开始下标
//		int start=(page-1)*rows;
//
//	List<Item> itemList=	itemMapper.findItemByPage(start,rows);
//		System.out.println(itemList.size());

		//使用mp实现分页
		Page<Item> mpPage=new Page(page,rows);
		QueryWrapper<Item> qw =new QueryWrapper<Item>();
		qw.orderByDesc("updated");
		IPage<Item> itemIPage = itemMapper.selectPage(mpPage, qw);
		return new EasyUITable(itemIPage.getTotal(), itemIPage.getRecords());
	}

	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);

	}

	@Override
	public void updateItem(Item item) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
	}

	@Override
	public void deleteItem(Long[] ids) {
		itemMapper.deleteByIds(ids);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectById(itemId);
	}
}
