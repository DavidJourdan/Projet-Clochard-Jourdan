package dijkstra;

import java.util.ArrayList;

public class Dijkstra {
    private static PreviousInterface dijkstra(ASetInterface a, GraphInterface g, PiInterface pi,
                                              PreviousInterface previous, VertexInterface r) {
    	ArrayList<VertexInterface> allVert = g.getAllVertices() ; // tous les sommets du graphe dans une liste
    	int len=allVert.size(); // Nombre de sommets
    	a.add(r);
    	pi.setValue(0,r);
    	for (VertexInterface x : allVert)
    		pi.setValue(Integer.MAX_VALUE, x); // L'infini à tous les autres sommmets
    	VertexInterface piv = r ;
    	int piPiv = 0;
    	int j=1;
    	while (j<len){
    		ArrayList<VertexInterface> pivsucc=g.getSuccessors(piv);
    		for (VertexInterface y : pivsucc){
    			if (! a.contains(y)){
    				int p=g.getWeight(piv, y);
    				if (piPiv+p<pi.getValue(y));
    				pi.setValue(p+pi.getValue(piv), y);
    				previous.setValue(piv, y);
    			}
    		}
    		/** Définition d'un nouveau pivot */
    		VertexInterface newPiv=null;
    		int piNewPiv = Integer.MAX_VALUE;
    		for (VertexInterface s : allVert)
    			if (! a.contains(s)){
    				int piS=pi.getValue(s);
    				if (piS<piNewPiv){
    					newPiv = s;
    					piNewPiv=piS;
    				}
    			}
    		piv=newPiv;
    		piPiv=piNewPiv;
    		a.add(piv);
    		j++;
    	}
    	return previous;
    }
}
