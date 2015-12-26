package maze;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

import java.io.*;
import java.util.ArrayList;


public class Maze implements GraphInterface {

    private ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>();
    private String fileName;
    private int height, width;
    
    private final MBox[][] boxes;


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        boxes = new MBox[height][width];
    }

    // Retourne les informations de la case
    public final MBox getBox(int x,int y){
    	return boxes[x][y];
    }
    
    
    public VertexInterface getVertex(int x, int y) {
        for(VertexInterface v : vertices) {
            if(v.getX()==x && v.getY()==y)
                return v;
        }
        return null;
    }

    
    /** On ajoute toutes les cases du labyrinthe
    à une liste vide */
    public ArrayList<VertexInterface> getAllVertices() {
    	ArrayList<VertexInterface> allVert = new ArrayList<VertexInterface>();
    	
    	for (int x=0 ; x<height; x++){
    		MBox[] line = boxes[x];
    		for (int y=0; y<width; y++){
    			allVert.add(line[y]);
    		}
    	}
    	return allVert;
    }

    // Les successeurs sont les cases sur lesquelles on peut se rendre
    // On ajoute tous les sommets à une liste vide
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
    	ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
    	
    	// Chaque sommet doit être une case du labyrinthe
    	// On fait donc un transtypage
    	MBox box = (MBox)vertex;
    	
    	int x = box.getLine();
        int y = box.getColumn();
        
        // On regarde chaque case voisine
        
        // D'abord celui au-dessus
        if (x>0){ // Ne fonctionne pas si pas de voisin au-dessus...
        	MBox topbox = boxes[x-1][y];
        	if (topbox.cangothere())
        		successors.add(topbox);
        }
        // Puis celui en-dessous
        if (x<height-1){
        	MBox underbox = boxes[x+1][y];
        	if (underbox.cangothere())
        		successors.add(underbox);
        }
        
        // Celui à gauche
        if (y>0){
        	MBox leftbox = boxes[x][y-1];
        	if (leftbox.cangothere())
        		successors.add(leftbox);
        }
        
        // Et enfin, le voisin de droite
        if (y<width-1){
        	MBox rightbox = boxes[x][y+1];
        	if (rightbox.cangothere())
        		successors.add(rightbox);
        }
        return successors;
    }

    // Le poids de chaque arc vaut 1
    public int getWeight(VertexInterface src, VertexInterface dst) {
            return 1;
    }

    public final void initFromTextFile(String fileName) throws MazeReadingException {
        FileReader fin = null;
        BufferedReader bin = null;
        try {
            fin = new FileReader(fileName);
            bin = new BufferedReader(fin);
            
            String str = bin.readLine();
            int x = 0, y = 0;
            while(x < height) {
            	// Si la longueur du texte n'est pas de la même taille que le texte, il y a une erreur.
                if(str.length()!= width) {
                    throw new MazeReadingException(fileName, x,"Invalid line length");
                }
                char[] ch = str.toCharArray();
                for(char c : ch) {
                    switch(c) {
                    	// Lorsque la lettre rencontrée est A, E, D ou W, on la met dans la case
                        case('A'):
                            ABox a = new ABox(x,y,this);
                            boxes[x][y]=a;
                            break;
                        case('E'):
                            EBox e = new EBox(x,y,this);
                            boxes[x][y]=e;
                            break;
                        case('D'):
                            DBox d = new DBox(x,y,this);
                            boxes[x][y]=d;
                            break;
                        case('W'):
                            WBox w = new WBox(x,y,this);
                            boxes[x][y] = w;
                            break;
                        // Si la lettre n'est ni E, ni A, ni D, ni W, alors il y a une erreur dans le texte.
                        default:
                            throw new MazeReadingException(fileName, x, "Character not supported");
                    }
                    y++;
                }
                str = bin.readLine();
                x++;
            }
            if(str != null) {
                bin.close();
                throw new MazeReadingException(fileName, x, "Invalid number of lines");
            }
            bin.close();
        
        } catch (FileNotFoundException e) { //Fichier non trouvé
        	System.err.println("Error 404: File not Found");
            e.printStackTrace(System.err); // Erreur inconnue
        } catch (IOException e) {
        	System.err.println("Error: read error");
            e.printStackTrace(System.err);
        } catch (Exception e) {
        	System.err.println("Error: unknown error");
            e.printStackTrace(System.err);
        } finally {
        	if (fin!= null)
				try { fin.close(); } catch (Exception e) {}
        	// On ferme FileReader
        	if (bin!= null)
				try { bin.close(); } catch (Exception e) {}
        	// On ferme BufferedReader
        }
    }

    public final void saveToTextFile(String fileName) throws IOException {
        try {
            FileWriter     fw = new FileWriter(fileName, false) ;
            BufferedWriter bw = new BufferedWriter(fw) ;
            PrintWriter    pw = new PrintWriter(bw) ;
            for(int i=0; i<height; i++)
            {
                String str = "";
                for(int j=0; j<width; j++)
                {
                    MBox m = boxes[i][j];
                    str = str.concat(m.getLabel());
                }
                pw.println(str);
            }
            pw.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
