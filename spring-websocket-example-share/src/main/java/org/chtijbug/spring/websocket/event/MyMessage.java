package org.chtijbug.spring.websocket.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MyMessage {

    public static class MyMessageStream extends JSONCoder<MyMessage> {
        public MyMessageStream() {
        }

        public MyMessageStream(Class<MyMessage> clazz) {
            set_type(clazz);
        }
    }

    private String title;
    private String content;

    public MyMessage() {
    }

    public MyMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("content", content)
                .toString();
    }
}
