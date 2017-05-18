package com.anefyodov.htmlparser.t;

import com.siliconvd.user.api.t.Coords;
import com.siliconvd.user.api.t.Node;
import com.siliconvd.user.api.t.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PathFinder {
    private final int[][] matrix;
    private final Map<Node, Set<Node>> graph;

    public PathFinder(int[][] d) {
        this.matrix = d;

        final Map<Coords, Node> convert = convert(matrix);
        final Map<Node, Set<Node>> graph = connect(convert);

        this.graph = graph;

    }

    private List<Path> findLongerPaths(Map<Node, Set<Node>> graph, List<Path> previousPaths) {
        List<Path> allNewPaths = new ArrayList<>();
        for (Path previousPath : previousPaths) {
            List<Path> newPaths = previousPath.getLongerPaths(graph);
            allNewPaths.addAll(newPaths);
        }
        return allNewPaths;
    }

    public void filterOnlyNeeded(Map<Node, Set<Node>> graph, int needed) {
        final Iterator<Node> iterator = graph.keySet().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (!(node.getValue()==needed)) {
                iterator.remove();
            }
        }
    }

    public Map<Node, Set<Node>> connect(Map<Coords, Node> map) {
        Map<Node, Set<Node>> graph = new HashMap<>();
        for (Map.Entry<Coords, Node> coordsNodeEntry : map.entrySet()) {
            final HashSet<Node> nodes = new HashSet<>();
            final Coords left = new Coords(coordsNodeEntry.getKey().getI() - 1, coordsNodeEntry.getKey().getJ());
            final Coords top = new Coords(coordsNodeEntry.getKey().getI(), coordsNodeEntry.getKey().getJ() - 1);
            final Coords right = new Coords(coordsNodeEntry.getKey().getI()+1, coordsNodeEntry.getKey().getJ());
            final Coords down = new Coords(coordsNodeEntry.getKey().getI(), coordsNodeEntry.getKey().getJ() +1 );
            addToNodes(nodes, left, map);
            addToNodes(nodes, top, map);
            addToNodes(nodes, right, map);
            addToNodes(nodes, down, map);
            graph.put(coordsNodeEntry.getValue(), nodes);
        }
        return graph;
    }

    private void addToNodes(Set<Node> nodes, Coords left, Map<Coords, Node> map) {
        Optional.ofNullable(map.get(left)).ifPresent(nodes::add);
    }

    public Map<Coords, Node> convert(int[][] matrix) {
        Map<Coords, Node> map = new HashMap<>();
        for (int i=0 ;i< matrix.length;i++) {
            for (int j=0 ; j< matrix[i].length; j++) {
                Coords coords = new Coords(i,j);
                Node node = new Node(matrix[i][j], i, j);
                map.put(coords, node);
            }
        }
        return map;
    }

    public List<Path> getPath(int i, int j) {
        int needed= this.matrix[i][j];
        filterOnlyNeeded(graph, needed);

        List<Path> paths = new ArrayList<>();
        List<Path> previousPaths = new ArrayList<>();
        final List<Node> ts = Collections.singletonList(new Node(needed, i, j));
        paths.add(new Path(ts));
        do {
            previousPaths.clear();
            previousPaths.addAll(paths);
            paths = findLongerPaths(graph, previousPaths);
        } while (paths.size()>0);
        return previousPaths;
    }
}
