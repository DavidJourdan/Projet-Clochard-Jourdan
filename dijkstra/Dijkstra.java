package dijkstra;

import java.util.ArrayList;

public class Dijkstra {
    public static PreviousInterface dijkstra (GraphInterface g, VertexInterface r) {
        ASet a= new ASet();
        Previous previous=new Previous();
        Pi pi=new Pi();
        return dijkstra(a, g , pi, previous, r );
    }

    private static PreviousInterface dijkstra(ASetInterface a, GraphInterface g, PiInterface pi,
                                              PreviousInterface previous, VertexInterface r) {
        a.add(r);
        VertexInterface pivot = r;
        ArrayList<VertexInterface> allVertices = g.getAllVertices();
        int pivotValue = 0;
        int len = allVertices.size();
        for(VertexInterface v : allVertices) {
            pi.setValue(v, Integer.MAX_VALUE);
        }
        pi.setValue(r, 0);
        for(int j=1; j<len; j++) {
            ArrayList<VertexInterface> successors = g.getSuccessors(pivot);
            for(VertexInterface y : successors) {
                int weightY = g.getWeight(pivot, y);
                if(!a.contains(y) && pivotValue + weightY < pi.getValue(y)) {
                    pi.setValue(y, pivotValue + weightY);
                    previous.setValue(y, pivot);
                }
            }
            int min = Integer.MAX_VALUE;
            for (VertexInterface s : allVertices) {
                if (!a.contains(s) && pi.getValue(s) < min) {
                    pivot = s;
                    min = pi.getValue(s);
                }
            }
            pivotValue = min;
            a.add(pivot);
        }
        return previous;
    }
}
