package server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import server.bean.dto.ElvesDTO;
import server.bean.vo.ElvesVO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@Mapper
public interface ElvenDao extends BaseMapper<ElvesDTO> {

    @Insert("insert into tb_picture (owner_id, picture_id, link) values (#{ownerId}, #{pictureId}, #{link})")
    void addLink(String ownerId, String pictureId, String link);

    @Select("select link from tb_picture where owner_id = #{ownerId}")
    List<String> getPictureList(String ownerId);

    @Select("select * from tb_elves where kind = #{kind} order by create_time DESC limit #{startIndex}, #{size}")
    List<ElvesDTO> getElvenListByKind(String kind, int startIndex, int size);

    @Select("select count(*) from tb_like where user_id = #{userId} and target_id = #{targetId}")
    int haveLike(int userId, String targetId);

    @Delete("delete from tb_like where user_id = #{userId} and target_id = #{targetId}")
    void disLike(int userId, String targetId);

    @Insert("insert into tb_like (user_id, target_id) values (#{userId}, #{targetId})")
    void like(int userId, String targetId);

    @Update("update tb_elves set like_number = like_number - 1 where elves_id = #{targetId}")
    void likeNumberSubtract(String targetId);

    @Update("update tb_elves set like_number = like_number + 1 where elves_id = #{targetId}")
    void likeNumberAdd(String targetId);

    @Select("select * from tb_elves order by like_number DESC limit #{startIndex}, #{size}")
    List<ElvesDTO> getElvenHotList(int startIndex, int size);

    @Select("select * from tb_elves order by create_time DESC limit #{startIndex}, #{size}")
    List<ElvesDTO> getElvenList(int startIndex, int size);

    @Select("select distinct kind from tb_elves_kind")
    List<String> getElvenKindList();

    @Select("select * from tb_elves where elves_id = #{targetId}")
    ElvesDTO getElvenById(String targetId);
}
