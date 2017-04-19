/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * $Id: SubscriptionData.java 1835 2013-05-16 02:00:50Z shijia.wxr $
 */
package com.alibaba.rocketmq.common.protocol.heartbeat;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashSet;
import java.util.Set;


/**
 * @author shijia.wxr　　 订阅信息和TAG topic信息   存储在 RebalanceImpl.subscriptionInner中  这里面存有关注的tag相关的信息
 *
 * 在buildSubscriptionData获取订阅信息
 */
public class SubscriptionData implements Comparable<SubscriptionData> {
    public final static String SUB_ALL = "*";
    private boolean classFilterMode = false;
    //订阅的topic。  这个topic可能是RETRY_GROUP_TOPIC_PREFIX+ConsumerGroup,见DefaultMQPushConsumerImpl.start.copySubscription
    private String topic;
    //订阅表达式。
    private String subString;
    //subString被分解得到的tag集合。
    private Set<String> tagsSet = new HashSet<String>();
    //tagSet中的tag hashcode 集合。
    private Set<Integer> codeSet = new HashSet<Integer>();
    //订阅版本号， 默认用当前时间。
    private long subVersion = System.currentTimeMillis();

    @JSONField(serialize = false)
    private String filterClassSource;


    public String getFilterClassSource() {
        return filterClassSource;
    }


    public void setFilterClassSource(String filterClassSource) {
        this.filterClassSource = filterClassSource;
    }


    public SubscriptionData() {

    }


    public SubscriptionData(String topic, String subString) {
        super();
        this.topic = topic;
        this.subString = subString;
    }


    public String getTopic() {
        return topic;
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getSubString() {
        return subString;
    }


    public void setSubString(String subString) {
        this.subString = subString;
    }


    public Set<String> getTagsSet() {
        return tagsSet;
    }


    public void setTagsSet(Set<String> tagsSet) {
        this.tagsSet = tagsSet;
    }


    public long getSubVersion() {
        return subVersion;
    }


    public void setSubVersion(long subVersion) {
        this.subVersion = subVersion;
    }


    public Set<Integer> getCodeSet() {
        return codeSet;
    }


    public void setCodeSet(Set<Integer> codeSet) {
        this.codeSet = codeSet;
    }


    public boolean isClassFilterMode() {
        return classFilterMode;
    }


    public void setClassFilterMode(boolean classFilterMode) {
        this.classFilterMode = classFilterMode;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (classFilterMode ? 1231 : 1237);
        result = prime * result + ((codeSet == null) ? 0 : codeSet.hashCode());
        result = prime * result + ((subString == null) ? 0 : subString.hashCode());
        result = prime * result + ((tagsSet == null) ? 0 : tagsSet.hashCode());
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubscriptionData other = (SubscriptionData) obj;
        if (classFilterMode != other.classFilterMode)
            return false;
        if (codeSet == null) {
            if (other.codeSet != null)
                return false;
        }
        else if (!codeSet.equals(other.codeSet))
            return false;
        if (subString == null) {
            if (other.subString != null)
                return false;
        }
        else if (!subString.equals(other.subString))
            return false;
        if (subVersion != other.subVersion)
            return false;
        if (tagsSet == null) {
            if (other.tagsSet != null)
                return false;
        }
        else if (!tagsSet.equals(other.tagsSet))
            return false;
        if (topic == null) {
            if (other.topic != null)
                return false;
        }
        else if (!topic.equals(other.topic))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "SubscriptionData [classFilterMode=" + classFilterMode + ", topic=" + topic + ", subString="
                + subString + ", tagsSet=" + tagsSet + ", codeSet=" + codeSet + ", subVersion=" + subVersion
                + "]";
    }


    @Override
    public int compareTo(SubscriptionData other) {
        String thisValue = this.topic + "@" + this.subString;
        String otherValue = other.topic + "@" + other.subString;
        return thisValue.compareTo(otherValue);
    }
}
