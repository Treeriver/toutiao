package com.nowcoder.async;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface EventHandler {

    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();


}
