package com.anefyodov.htmlparser.t;

import com.siliconvd.user.api.t.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Path {
    private final List<Node> path = new ArrayList<>();

    public Path(List<Node> path) {
        this.path.addAll(path);
    }

    public Path(List<Node> path, Node neighbour) {
        this.path.addAll(path);
        this.path.add(neighbour);
    }

    public List<com.siliconvd.user.api.t.Path> getLongerPaths(Map<Node, Set<Node>> graph) {
        List<com.siliconvd.user.api.t.Path> list = new ArrayList<>();
        final Node lastNode = this.path.get(this.path.size() - 1);
        final Set<Node> neighours = graph.get(lastNode);
        if (neighours==null) {
            return Collections.emptyList();
        }
        for (Node neighour : neighours) {
            if (neighour.getValue() != lastNode.getValue()) {
                continue;
            }
            if (!this.path.contains(neighour)) {
                final com.siliconvd.user.api.t.Path path = new com.siliconvd.user.api.t.Path(this.path, neighour);
                list.add(path);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "Path{" +
                "path=" + path +
                '}';
    }
}
