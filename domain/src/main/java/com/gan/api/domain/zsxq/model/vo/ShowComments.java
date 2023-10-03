package com.gan.api.domain.zsxq.model.vo;


import java.time.LocalDateTime;
import java.util.List;

public class ShowComments {

    // 评论内容
    private List<String> text;

    private List<LocalDateTime> create_time;

    public List<LocalDateTime> getCreate_time() {
        return create_time;
    }

    public void setCreate_time(List<LocalDateTime> create_time) {
        this.create_time = create_time;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
