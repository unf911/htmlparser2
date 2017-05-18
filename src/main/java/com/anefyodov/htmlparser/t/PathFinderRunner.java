package com.anefyodov.htmlparser.t;

import com.siliconvd.user.api.t.PathFinder;

public class PathFinderRunner {
    public static void main(String args[]) {
        int[][] matrix = new int[4][3];
        matrix[1][1]=5;
        com.siliconvd.user.api.t.PathFinder pathFinder = new PathFinder(matrix);
        pathFinder.getPath(1, 1);
    }


}
