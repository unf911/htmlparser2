package com.anefyodov.htmlparser;

import com.siliconvd.user.api.t.Coords;
import com.siliconvd.user.api.t.Node;
import com.siliconvd.user.api.t.Path;
import com.siliconvd.user.api.t.PathFinder;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPathFinder {
    @Test
    public void shouldRun() {
        int[][] matrix = getMartix();
        final PathFinder pathFinder = new PathFinder(matrix);
        final Map<Coords, Node> convert = pathFinder.convert(matrix);
        assertThat(convert.size()).isEqualTo(12);
        System.out.println(convert);
    }

    @Test
    public void shouldConnect() {
        int[][] matrix = getMartix();
        final PathFinder pathFinder = new PathFinder(matrix);
        final Map<Coords, Node> convert = pathFinder.convert(matrix);
        final Map<Node, Set<Node>> connect = pathFinder.connect(convert);
        final Set<Node> nodes = connect.get(new Node(0, 0, 0));
        assertThat(nodes).hasSize(2);
    }

    @Test
    public void shouldFilter() {
        int[][] matrix = getMartix();
        matrix[2][1]=5;
        final PathFinder pathFinder = new PathFinder(matrix);
        final Map<Node, Set<Node>> graph = pathFinder.connect(pathFinder.convert(matrix));
        pathFinder.filterOnlyNeeded(graph, 5);
        assertThat(graph.size()).isEqualTo(2);
        System.out.println(graph);
    }

    @Test
    public void shouldPath() {
        int[][] matrix = getMartix();
        matrix[2][1]=5;
        matrix[2][2]=5;
        final PathFinder pathFinder = new PathFinder(matrix);
        final List<Path> paths = pathFinder.getPath(1, 1);
        System.out.println(paths);
    }


    private int[][] getMartix() {
        int[][] matrix = new int[4][3];
        matrix[1][1]=5;
        return matrix;
    }
}
