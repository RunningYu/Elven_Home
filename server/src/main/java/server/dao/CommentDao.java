package server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import server.bean.dto.CommentDTO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentDTO> {
    /**
     * 评论的回复量+1
     */
    @Update("update tb_comment set reply_number = reply_number + 1 where comment_id = #{commentId} and reply_id = #{no}")
    void replyNumberAdd(String commentId, String no);

    @Select("select * from tb_comment where dynamic_id = #{dynamicId} and reply_id = #{no} order by create_time DESC limit #{startIndex}, #{size}")
    List<CommentDTO> getCommentList(String dynamicId, String no, int startIndex, int size);

    @Select("select * from tb_comment where comment_id = #{commentId}  order by create_time DESC limit #{startIndex}, #{size}")
    List<CommentDTO> getReplyList(String commentId, int startIndex, int size);
}
