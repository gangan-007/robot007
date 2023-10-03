package com.gan.api.application.job;


import com.alibaba.fastjson.JSON;
import com.gan.api.domain.ai.IOpenAI;
import com.gan.api.domain.zsxq.IZsxqApi;
import com.gan.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.gan.api.domain.zsxq.model.vo.ShowComments;
import com.gan.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@Component
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    // 表达式：cron.qqe2.com
    @Scheduled(cron = "0/30 * * * * ?")
    public void run() {
        try {
            /*if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }*/

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            // logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待会答问题");
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(0);
            List<ShowComments> showComments = topic.getShow_comments();
            List<String> text = showComments.get(showComments.size() - 1).getText();
            List<LocalDateTime> createTime = showComments.get(showComments.size() - 1).getCreate_time();
            String str = text.get(text.size() - 1);
            LocalDateTime now = createTime.get(text.size() - 1);
            System.out.println("创建时间now：" + now);

            if ((LocalDateTime.now().getSecond() - now.getSecond()) < 15) {
                logger.info("向chatgpt提问问题str：{}", JSON.toJSONString(str));
                String answer = openAI.doChatGPT(str.trim());
                logger.info("chatgpt回应：{}", answer);

                // 3. 问题回复
                boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
                logger.info("在知识星球评论状态成功：{}", status);
            } else {
                logger.info("打烊中...");
            }
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }
    }



}
