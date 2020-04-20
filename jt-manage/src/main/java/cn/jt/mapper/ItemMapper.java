package cn.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.jt.pojo.Item;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{

    @Select("select * from tb_item order by updated desc limit #{start} ,#{rows}")
    List<Item> findItemByPage(int start, int rows);

    void deleteByIds(Long[] ids);
}
