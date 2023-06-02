package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import server.bean.dto.CommentDTO;
import server.bean.vo.CommentVO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
public interface CommentService extends IService<CommentDTO> {
    void addComment(CommentDTO commentDTO);

    List<CommentVO> getCommentList(String dynamicId, int page, int size);

    List<CommentVO> getReplyList(String commentId, Integer userId, int page, int size);
}
