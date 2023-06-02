package server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import server.Mapper.ServerMapper;
import server.bean.dto.CommentDTO;
import server.bean.dto.UserDTO;
import server.bean.vo.CommentVO;
import server.dao.CommentDao;
import server.dao.DynamicDao;
import server.dao.UserDao;
import server.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentDTO> implements CommentService {

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DynamicDao dynamicDao;

    @Autowired
    private CommentDao commentDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addComment(CommentDTO commentDTO) {
        String uuid = UUID.randomUUID().toString().replace('-', '1' );
        if (StringUtils.isEmpty(commentDTO.getCommentId())) {
            commentDTO.setCommentId(uuid);
        } else {
            commentDTO.setReplyId(uuid);
        }
        commentDao.insert(commentDTO);

        // 动态评论量+1
        dynamicDao.commentNumberAdd(commentDTO.getDynamicId());

        // 评论的回复量+1
        if ( !StringUtils.isEmpty(commentDTO.getCommentId()) && !StringUtils.isEmpty(commentDTO.getReplyId()) ) {
            commentDao.replyNumberAdd(commentDTO.getCommentId(), "no");
            System.out.println("===============================");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<CommentVO> getCommentList(String dynamicId, int page, int size) {
        int startIndex = (page - 1) * size;
        List<CommentDTO> commentDTOList = commentDao.getCommentList(dynamicId, "no", startIndex, size);
        List<CommentVO> commentVOList = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOList) {
            UserDTO user = userDao.getUserByUserId(commentDTO.getUserId());
            CommentVO commentVO = serverMapper.commentDTO2CommentVO(commentDTO);
            if ( !ObjectUtil.isEmpty(user) ) {
                commentVO.setUserName(user.getUsername());
                commentVO.setUserHeadPicture(user.getHeadPicture());
            }
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<CommentVO> getReplyList(String commentId, Integer userId, int page, int size) {
        int startIndex = (page - 1) * size;
        List<CommentDTO> commentDTOList = commentDao.getReplyList(commentId, startIndex, size);
        List<CommentVO> commentVOList = new ArrayList<>();
        UserDTO commentUser = userDao.getUserByUserId(userId);
        for (CommentDTO commentDTO : commentDTOList) {
            UserDTO user = userDao.getUserByUserId(commentDTO.getUserId());
            CommentVO commentVO = serverMapper.commentDTO2CommentVO(commentDTO);
            if ( !ObjectUtil.isEmpty(user) ) {
                commentVO.setUserName(user.getUsername());
                commentVO.setUserHeadPicture(user.getHeadPicture());
            }
            if ( !ObjectUtil.isEmpty(commentUser) ) {
                commentVO.setRepliedUserName(commentUser.getUsername());
                commentVO.setRepliedUserHeadPicture(commentUser.getHeadPicture());
            }
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }
}
