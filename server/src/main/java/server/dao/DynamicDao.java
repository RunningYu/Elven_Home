package server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import server.bean.dto.DynamiskDTO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Mapper
public interface DynamicDao extends BaseMapper<DynamiskDTO> {

    /**
     * 动态的评论量+1
     */
    @Update("update tb_dynamisk set comment_number = comment_number + 1 where dynamic_id = #{dynamicId}")
    void commentNumberAdd(String dynamicId);

    /**
     * 分页查询动态列表
     */
    @Select("select * from tb_dynamisk  order by create_time DESC limit #{startIndex}, #{size} ")
    List<DynamiskDTO> getDynamicList(int startIndex, int size);

    @Insert("insert into tb_dynamisk (dynamic_id, user_id, content, type) values (#{dynamicId}, #{userId}, #{content}, #{type})")
    void addDynamic(DynamiskDTO dynamiskDTO);
}
