package com.lilei.router_compiler.wrapper;

import javax.lang.model.element.Element;

/**
 * Created by lilei
 * Date : 2018/9/26
 */

public class Node {
    private NodeType nodeType;
    private Element target;   // Destination
    private String host;
    private String path;            // Path of route
    private String desc;            // Desc of route
    private int priority = -1;      // The smaller the number, the higher the priority

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Element getTarget() {
        return target;
    }

    public void setTarget(Element target) {
        this.target = target;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
