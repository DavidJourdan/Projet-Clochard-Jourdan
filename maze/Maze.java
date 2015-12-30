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
                if(str.length()!= width)
                    throw new MazeReadingException(fileName, x,"Invalid column length");
                while (y<width) {
                        switch(str.charAt(y)) {
                        	// Lorsque la lettre rencontrée est A, E, D ou W, on la met dans la case
                            case('A'):
                            	boxes[x][y] = new ABox(x,y,this);
                                break;
                            case('W'):
                            	boxes[x][y] = new WBox(x,y,this);
                                break;
                            case('E'):
                            	boxes[x][y] = new EBox(x,y,this);
                                break;
                            case('D'):
                            	boxes[x][y] = new DBox(x,y,this);
                                break;
                            // Si la lettre n'est ni E, ni A, ni D, ni W, alors il y a une erreur dans le texte.
                            default:
                                throw new MazeReadingException(fileName, x, "Character not supported");
                        }

                    y++;
                }
                x++;
            }
            if(str == null) {
                throw new MazeReadingException(fileName, x, "Invalid number of lines");
            }       
        } catch (FileNotFoundException e) { //Fichier non trouvé
        	System.err.println("Error 404: File not Found");
            e.printStackTrace(System.err);
        } catch (IOException e) { // Erreur de lecture
        	System.err.println("Error: read error");
            e.printStackTrace(System.err);
        } catch (Exception e) { // Erreur inconnue
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

    public final void saveToTextFile(String fileName) throws FileNotFoundException {
    	PrintWriter pw = null;
    	try {
            pw = new PrintWriter(fileName) ;
            // On regarde chaque case et on récupère le caractère correspondant pour écrire dans le fichier
            for(int i=0; i<height; i++) // Lignes
            {
                for(int j=0; j<width; j++){ // Colonnes
                	MBox box = boxes[i][j];
                    box.writeTo(pw);} // Chaque case est mise dans le fichier
                pw.println(); // On change de ligne quand on a fini d'en étudier une
            }
        } catch (FileNotFoundException e) { // Fichier non trouvé
        	System.err.println("Error 404: File not Found \"" + fileName + "\""); 
        } catch (SecurityException e) { // Erreur de sécurité
        	System.err.println("Security Error \"" + fileName + "\"");
        } catch (Exception e) { // Erreur inconnue
        	System.err.println("Unknown Error");
            e.printStackTrace(System.err);
        } finally {
        	if (pw != null)
				try { pw.close(); } catch (Exception e) {}
        }
    }
}
