import java.util.*;
import java.awt.Point;

class Solution {
    private Point[][] parent;
    private String[][] cells;
    private List<String> answerList;
    
    private Point find(int r, int c) {
        if (parent[r][c].x == r && parent[r][c].y == c) {
            return parent[r][c];
        }
        Point p = find(parent[r][c].x, parent[r][c].y);
        parent[r][c] = p;
        return p;
    }
    
    private void union(int r1, int c1, int r2, int c2) {
        parent[r2][c2] = parent[r1][c1];
    }
    
    private void updateToVal(int r, int c, String data) {
        Point p = find(r, c);
        cells[p.x][p.y] = data;
    }
    
    private void updateVal1ToVal2(String data1, String data2) {
        for (int r = 0; r < 51; r++) {
            for (int c = 0; c < 51; c++) {
                Point p = find(r, c);
                if (cells[p.x][p.y].equals(data1)) {
                    cells[p.x][p.y] = data2;
                }
            }
        }
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        Point p1 = find(r1, c1);
        Point p2 = find(r2, c2);
        
        if (p1.x == p2.x && p1.y == p2.y) {
            return;
        }
        
        if (!cells[p1.x][p1.y].equals("EMPTY")) {
            union(p1.x, p1.y, p2.x, p2.y);
        } else {
            union(p2.x, p2.y, p1.x, p1.y);
        }
    }
    
    private void unmerge(int r, int c) {
        Point p = find(r, c);
        String data = cells[p.x][p.y];
        List<Point> mergeList = new ArrayList<>();
        
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                Point pi = find(i, j);
                if (pi.x == p.x && pi.y == p.y) {
                    mergeList.add(new Point(i, j));
                }
            }
        }
        
        for (Point point : mergeList) {
            parent[point.x][point.y] = new Point(point.x, point.y);
            if (point.x == r && point.y == c) {
                cells[point.x][point.y] = data;
            } else {
                cells[point.x][point.y] = "EMPTY";
            }
        }
    }
    
    private void print(int r, int c) {
        Point p = find(r, c);
        answerList.add(cells[p.x][p.y]);
    }
    
    public String[] solution(String[] commands) {
        // Initialize fields
        parent = new Point[51][51];
        cells = new String[51][51];
        answerList = new ArrayList<>();
        
        for (int r = 0; r < 51; r++) {
            for (int c = 0; c < 51; c++) {
                parent[r][c] = new Point(r, c);
                cells[r][c] = "EMPTY";
            }
        }
        
        for (String command : commands) {
            String[] tokens = command.split(" ");
            
            if (tokens[0].equals("UPDATE")) {
                if (tokens.length == 4) {
                    int r = Integer.parseInt(tokens[1]);
                    int c = Integer.parseInt(tokens[2]);
                    updateToVal(r, c, tokens[3]);
                } else {
                    updateVal1ToVal2(tokens[1], tokens[2]);
                }
            } else if (tokens[0].equals("MERGE")) {
                int r1 = Integer.parseInt(tokens[1]);
                int c1 = Integer.parseInt(tokens[2]);
                int r2 = Integer.parseInt(tokens[3]);
                int c2 = Integer.parseInt(tokens[4]);
                merge(r1, c1, r2, c2);
            } else if (tokens[0].equals("UNMERGE")) {
                int r = Integer.parseInt(tokens[1]);
                int c = Integer.parseInt(tokens[2]);
                unmerge(r, c);
            } else if (tokens[0].equals("PRINT")) {
                int r = Integer.parseInt(tokens[1]);
                int c = Integer.parseInt(tokens[2]);
                print(r, c);
            }
        }
        
        return answerList.toArray(new String[0]);
    }
}