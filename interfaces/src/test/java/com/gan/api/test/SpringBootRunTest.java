package com.gan.api.test;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {


    /**

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            logger.debug("topic:===========");
            logger.debug("topic:{}", JSON.toJSONString(topic));
            String topicId = topic.getTopic_id();

            if (topic.getComments_count() > 0) {
                // 自言自语
                String hi = "hi!";
                logger.info("topicId：{} text：{}", topicId, hi);
                // 回答问题
                zsxqApi.answer(groupId, cookie, topicId, hi, false);
            }
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("Hell world!");
        logger.info("测试结果：{}", response);
    }

    @Autowired
    private ChatbotSchedule chatbotSchedule;

    @Test
    public void testAI() throws IOException {
        chatbotSchedule.run();
        logger.info("测试结果end");
    }

   */

}
