package maze;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Maze implements GraphInterface {

    private ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>(); // tableau contenant les cases franchissables
    private final int height, width;
    private MBox[][] boxes;    // tableau contenant toutes les cases du labyrinthe
    private DBox departure;
    private ABox arrival;


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        boxes = new MBox[height+2][width+2];
        // on initialise un cadre de W
        for (int i=0 ; i < height+2 ; i++) {
            boxes[i][0]=new WBox(i, 0);
            boxes[i][width+1]=new WBox(i, width+1);
        }
        for (int j=1 ; j < width+1 ; j++) {
            boxes[0][j]=new WBox(0, j);
            boxes[height+1][j]=new WBox(height+1, j);
        }
        // pour la lisibilite, l'interieur est initialise avec des cases vides
        for (int i=1 ; i < height+1 ; i++) {
            for (int j=1 ; j < width+1 ; j++) {
                boxes[i][j] = new EBox(i,j);
            }
        }
    }

    public MBox getBox(int x, int y) {
    // Retourne la case aux informations demandees
    // ne renvoie le sommet que s'il est dans vertices
        if(x>=0 && x<height+2 && y>=0 && y<width+2)
            return boxes[x][y];
        else
            return null;
    }

    public void setBox(int x, int y, char letter){
    	switch (letter) 
		{
		case 'D' :
			new DBox(x, y); break;
		case 'A' :
			new ABox(x, y); break;
		case 'W' :
			new WBox(x, y); break;
		case 'E' :
			new EBox(x, y); break; 			
		}
    }

    public ArrayList<VertexInterface> getAllVertices() {
        return vertices;
    }

    // cherche dans boxes les cases franchissables autour de vertex
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();

        // Chaque sommet doit etre une case du labyrinthe
        // On fait donc un transtypage
        MBox box = (MBox) vertex;
        int x = box.getX();
        int y = box.getY();

        // un mur n'a pas de successeurs
        if(box.getType() == "W")
            return successors; // une liste vide

        // On regarde chaque case voisine

        // D'abord celui au-dessus
        MBox topBox = getBox(x + 1, y);
        if(topBox != null && !topBox.getType().equals("W"))
            successors.add(topBox);
        // Puis celui en-dessous
        MBox bottomBox = getBox(x - 1, y);
        if(bottomBox != null && !bottomBox.getType().equals("W"))
            successors.add(bottomBox);
        // Celui à gauche
        MBox leftBox = getBox(x, y - 1);
        if(leftBox != null && !leftBox.getType().equals("W"))
            successors.add(leftBox);
        // Et enfin, le voisin de droite
        MBox rightBox = getBox(x, y + 1);
        if(rightBox != null && !rightBox.getType().equals("W"))
            successors.add(rightBox);

        return successors;
    }

    public int getWeight(VertexInterface src, VertexInterface dst) {
        ArrayList<VertexInterface> successors = getSuccessors(src);
        if(successors.contains(dst))
            return 1;
        //Deux sommets non relies dans le graphe peuvent etre modelises par une arete de poids infini

        else {
            System.out.println("Error : no edge between" + src.getLabel()+" and "+ dst.getLabel());
            return Integer.MAX_VALUE;
        }
    }

    public final void initFromTextFile(String fileName) throws MazeReadingException {
        FileReader fin = null;
        BufferedReader bin = null;
        try {
            fin = new FileReader(fileName);
            bin = new BufferedReader(fin);
            String str = null;
            for(int x = 1; x<height+1; x++) {
                str = bin.readLine();
                // Si la longueur du texte n'est pas de la meme taille que le texte, il y a une erreur.
              if(str.length()!= width)
                    throw new MazeReadingException(fileName, x,"Invalid column length");
              
              for(int y = 1; y<width+1; y++) {
                    switch(str.charAt(y-1)) {
                        // Lorsque la lettre rencontree est A, E, D ou W, on la met dans la case
                        // Lorsque la lettre rencontree est A, E ou D, on la met dans la liste des cases franchissables
                        case('A'):
                            ABox a = new ABox(x,y);
                            boxes[x][y] = a;
                            vertices.add(a);
                            arrival = a;
                            break;
                        case('E'):
                            EBox e = new EBox(x,y);
                            boxes[x][y] = e;
                            vertices.add(e);
                            break;
                        case('D'):
                            DBox d = new DBox(x,y);
                            boxes[x][y] = d;
                            vertices.add(d);
                            departure = d;
                            break;
                        case('W'):
                            boxes[x][y] = new WBox(x,y);
                            break; // les W ne sont pas ajoutes dans vertices
                        // Si la lettre n'est ni E, ni A, ni D, ni W, alors il y a une erreur dans le texte.
                        default:
                            throw new MazeReadingException(fileName, x, String.format("Character not supported : %s", str.charAt(y)));
                    }
                }
            }
            if(str == null)
                throw new MazeReadingException(fileName, width - 1, "Invalid number of lines");
        } catch (FileNotFoundException e) { //Fichier non trouve
            System.err.println("Error 404: File not Found");
            e.printStackTrace(System.err);
        } catch (IOException e) { // Erreur de lecture
            System.err.println("Error: read error");
            e.printStackTrace(System.err);
        } catch (Exception e) { // Erreur inconnue
            System.err.println("Error: unknown error");
            e.printStackTrace(System.err);
        } finally {
            if (fin != null)
                try { fin.close(); } catch (Exception e) {}
           // On ferme FileReader
            if (bin != null);
              try { bin.close(); } catch (Exception e) {}
            // On ferme BufferedReader
        }
    }

    public final void saveToTextFile(String fileName) throws IOException {
        PrintWriter    pw = null;
        FileWriter     fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName, false) ;
            bw = new BufferedWriter(fw) ;
            pw = new PrintWriter(bw) ;
            // On regarde chaque case et on recupere le caractere correspondant pour ecrire dans le fichier
            for(int i=0; i<height; i++) {   // Lignes
                String str = "";
                for(int j=0; j<width; j++){ // Colonnes
                    MBox box = boxes[i+1][j+1];
                    str = str.concat(box.getType());
                }
                pw.print(str); // On saute une ligne quand on a fini d'en etudier une
                if(i<height-1)
                    pw.println();
            }
        } catch (FileNotFoundException e) { // Fichier non trouve
            System.err.println("Error 404: File not Found \"" + fileName + "\"");
        } catch (SecurityException e) { // Erreur de securite
            System.err.println("Security Error \"" + fileName + "\"");
        } catch (Exception e) { // Erreur inconnue
            System.err.println("Unknown Error");
            e.printStackTrace(System.err);
        } finally {
            if (pw != null)
                try { pw.close(); } catch (Exception e) {}
            if (fw != null)
                try { fw.close(); } catch (Exception e) {}
            if (bw != null)
                try { bw.close(); } catch (Exception e) {}
        }
    }

    public ArrayList<VertexInterface> solveMaze() {
        PreviousInterface prev = Dijkstra.dijkstra(this,departure);
        ArrayList<VertexInterface> path = prev.getShortestPathTo(arrival);
        return path;
    }
}
