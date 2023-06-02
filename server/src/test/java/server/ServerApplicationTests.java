package server;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.Mapper.ServerMapper;
import server.bean.ao.CommentAO;
import server.bean.ao.DynamiskAO;
import server.bean.ao.ElvenAO;
import server.bean.ao.UserAO;
import server.bean.dto.CommentDTO;
import server.bean.dto.DynamiskDTO;
import server.bean.dto.ElvesDTO;
import server.bean.dto.UserDTO;
import server.bean.vo.CommentVO;
import server.bean.vo.DynamicVO;
import server.dao.UserDao;
import server.nettyIM.command.ChatMessage;
import server.service.CommentService;
import server.service.DynamicService;
import server.service.ElvenService;
import server.service.UserService;
import server.utils.MD5;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class ServerApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ElvenService elvenService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private MD5 md5;

//    @Test
//    public void createJsonTest() {
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setStudentId("12003990130");
//        chatMessage.setContent("hello");
//        chatMessage.setTargetId("12003990121");
//        chatMessage.setType(1);
//        String messageJson = JSONObject.toJSONString(chatMessage);
//        System.out.println( messageJson );
//    }

    @Test
    void contextLoads() {
        System.out.println("test");
    }

    @Test
    void addStudentIdTest() {
        String startId = "120039901";
        int endId = 50;
        for ( int i = 1; i <= endId; i ++ ) {
            String studentId = "";
            if ( i / 10 == 0 ) {
                studentId = startId + "0" + i;
            } else {
                studentId = startId + i;
            }
            System.out.println("-------->" + studentId);
//            String password = md5.encrypt(studentId);
            userService.insertStudentId(studentId, "重庆理工大学");
        }
    }

    @Test
    void dynamicTest() {
        DynamiskAO dynamiskAO = new DynamiskAO();
        dynamiskAO.setUserId(1);
        dynamiskAO.setCollegeName("重庆理工大学");
        dynamiskAO.setContent("this cat is very nice!!!");
        dynamiskAO.setType(1);
        List<String> list = new ArrayList<>();
        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
        dynamiskAO.setLinks(list);
        DynamiskDTO dynamiskDTO = serverMapper.dynamiskAO2DynamiskDTO(dynamiskAO);
        System.out.println( "------------>" + dynamiskDTO );
        log.info("ServerApplicationTests dynamicTest dynamiskDTO:{}", dynamiskDTO);
        dynamicService.addDynamic(dynamiskDTO, dynamiskAO.getLinks());
    }

    @Test
    void getDynamicListTest() {
        List<DynamicVO> dynamicVOList = dynamicService.getDynamicList(1, 20);
        log.info("ServerApplicationTests dynamicTest dynamicVOList:{}", dynamicVOList);
    }

    @Test
    void addUserTest() {
        UserAO userAO = new UserAO();
        userAO.setUsername("腾讯爷");
        userAO.setPassword("12321kbuibuweg");
        userAO.setPhone("12345678910");
        userAO.setHeadPicture("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJl" +
                "bKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
        UserDTO userDTO = serverMapper.userAO2UserDTO(userAO);
        System.out.println( userDTO );
        userDao.insert(userDTO);
    }

    @Test
    void addCommentTest() {
        CommentAO commentAO = new CommentAO();
        commentAO.setContent("if you want to go to the music school, I can sing a song for you");
        commentAO.setDynamicId("9437edc61a4dd14a231b4d819aafab8013f2");
        commentAO.setUserId(1);
        CommentDTO commentDTO = serverMapper.commentAO2CommentDTO(commentAO);
        System.out.println("--------->" + commentDTO );
        commentService.addComment(commentDTO);
    }

    @Test
    void addReplyTest() {
        CommentAO commentAO = new CommentAO();
        commentAO.setContent("me too");
        commentAO.setDynamicId("9437edc61a4dd14a231b4d819aafab8013f2");
        commentAO.setCommentId("8a820643163861482b18c1f11e24b52be1cd");
        commentAO.setUserId(2);
        CommentDTO commentDTO = serverMapper.commentAO2CommentDTO(commentAO);
        System.out.println("--------->" + commentDTO );
        commentService.addComment(commentDTO);
    }

    @Test
    void getCommentListTest() {
        List<CommentVO> commentList = commentService.getCommentList("9437edc61a4dd14a231b4d819aafab8013f2", 1, 10);
        for (CommentVO commentVO : commentList) {
            System.out.println( commentVO );
        }
    }


    @Test
    void getReplyListTest() {
        List<CommentVO> commentList = commentService.getReplyList("8a820643163861482b18c1f11e24b52be1cd",1, 1, 10);
        for (CommentVO commentVO : commentList) {
            System.out.println( commentVO );
        }
    }

//    @Test
//    public void addElvenTest() {
//        ElvenAO elvenAO = new ElvenAO();
//        elvenAO.setHealth("健康");
//        elvenAO.setCollegeName("重庆理工大学");
//        elvenAO.setName("小黑");
//        elvenAO.setFirstFindPlace("菊轩");
//        elvenAO.setPersonality("很乖，可以让人抱");
//        elvenAO.setSex("雌");
//        elvenAO.setPlace("菊轩楼梯");
//        List<String> list = new ArrayList<>();
//        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
//        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
//        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
//        list.add("https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJlbKyaGIGGNjABT85XemdQ8AALIQQI-6pcAFPz9490.jpg");
//        elvenAO.setLinks(list);
//        System.out.println("--------------------------");
//        System.out.println( elvenAO );
//        System.out.println("--------------------------");
//        ElvesDTO elvesDTO = serverMapper.elvenAO2ElvesDTO(elvenAO);
//        System.out.println("--->" + elvesDTO);
//        elvenService.addElven(elvenAO);
//    }

}
