/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2019 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.extension.spiderAjax;

import java.util.HashMap;
import java.util.Map;
import org.zaproxy.zap.ZAP;
import org.zaproxy.zap.eventBus.Event;
import org.zaproxy.zap.eventBus.EventPublisher;
import org.zaproxy.zap.model.ScanEventPublisher;
import org.zaproxy.zap.model.Target;
import org.zaproxy.zap.users.User;

public class SpiderEventPublisher extends ScanEventPublisher {

    private static SpiderEventPublisher publisher = null;

    @Override
    public String getPublisherName() {
        return SpiderEventPublisher.class.getCanonicalName();
    }

    public static synchronized SpiderEventPublisher getPublisher() {
        if (publisher == null) {
            publisher = new SpiderEventPublisher();
            ZAP.getEventBus().registerPublisher(publisher, getEvents());
        }
        return publisher;
    }

    /*
     * Temporary method to allow the URL to be included, pending core changes
     */
    void publishScanEvent(
            EventPublisher publisher,
            String event,
            int scanId,
            Target target,
            String url,
            User user) {
        Map<String, String> map = new HashMap<>();
        map.put(SCAN_ID, Integer.toString(scanId));
        if (user != null) {
            map.put(USER_ID, Integer.toString(user.getId()));
            map.put(USER_NAME, user.getName());
        }
        map.put("url", url);
        ZAP.getEventBus().publishSyncEvent(publisher, new Event(publisher, event, target, map));
    }

    static synchronized void unregisterPublisher() {
        if (publisher == null) {
            return;
        }
        ZAP.getEventBus().unregisterPublisher(publisher);
    }

    public static void publishScanEvent(String event, int scanId) {
        SpiderEventPublisher publisher = getPublisher();
        publisher.publishScanEvent(publisher, event, scanId);
    }

    public static void publishScanEvent(
            String event, int scanId, Target target, String url, User user) {
        SpiderEventPublisher publisher = getPublisher();
        publisher.publishScanEvent(publisher, event, scanId, target, url, user);
    }
}
