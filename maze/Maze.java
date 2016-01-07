package maze;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

import java.io.*;
import java.util.ArrayList;


public class Maze implements GraphInterface {

    private ArrayList<VertexInterface> vertices = new ArrayList<>(); // tableau contenant les cases franchissables
    private int height, width;
    private MBox[][] boxes;    // tableau contenant toutes les cases du labyrinthe


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        boxes = new MBox[height][width];
    }

    // Retourne les informations de la case
    // ne renvoie le sommet que s'il est dans vertices
    public MBox getBox(int x, int y) {
        if(x>=0 && x<height && y>=0 && y<width)
            return boxes[x][y];
        else
            return null;
    }


    public ArrayList<VertexInterface> getAllVertices() {
        return vertices;
    }

    // cherche dans boxes les cases franchissables autour de vertex
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        ArrayList<VertexInterface> successors = new ArrayList<>();

        // Chaque sommet doit être une case du labyrinthe
        // On fait donc un transtypage
        MBox box = (MBox) vertex;
        int x = box.getX();
        int y = box.getY();

        // un mur n'a pas de successeurs
        if(box.getType() == "W")
            return successors;

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
        //Deux sommets non reliés dans le graphe peuvent être modélisés par une arête de poids infini
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
            for(int x = 0; x<height; x++) {
                str = bin.readLine();
                // Si la longueur du texte n'est pas de la même taille que le texte, il y a une erreur.
                if(str.length()!= width)
                    throw new MazeReadingException(fileName, x,"Invalid column length");
                for(int y = 0; y<width; y++) {
                    switch(str.charAt(y)) {
                        // Lorsque la lettre rencontrée est A, E, D ou W, on la met dans la case
                        // Lorsque la lettre rencontrée est A, E ou D, on la met dans la liste des cases franchissables
                        case('A'):
                            ABox a = new ABox(x,y);
                            boxes[x][y] = a;
                            vertices.add(a);
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
                            break;
                        case('W'):
                            boxes[x][y] = new WBox(x,y);
                            break; // les W ne sont pas ajoutés dans vertices
                        // Si la lettre n'est ni E, ni A, ni D, ni W, alors il y a une erreur dans le texte.
                        default:
                            throw new MazeReadingException(fileName, x, String.format("Character not supported : %s", str.charAt(y)));
                    }
                }
            }
            if(str == null)
                throw new MazeReadingException(fileName, width - 1, "Invalid number of lines");
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
            if (fin != null)
                try { fin.close(); } catch (Exception e) {}
            // On ferme FileReader
            if (bin != null)
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
            // On regarde chaque case et on récupère le caractère correspondant pour écrire dans le fichier
            for(int i=0; i<height-1; i++) {   // Lignes
                String str = "";
                for(int j=0; j<width; j++){ // Colonnes
                    MBox box = boxes[i][j];
                    str = str.concat(box.getType());
                } // Chaque case est mise dans le fichier
                pw.println(str); // On change de ligne quand on a fini d'en étudier une
            }
            // on ne veut pas d'espace à la fin du fichier sauvé
            String str = "";
            for(int j=0; j<width; j++){ // Colonnes
                MBox box = boxes[height-1][j];
                str = str.concat(box.getType());
            }
            pw.print(str);
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
            if (fw != null)
                try { fw.close(); } catch (Exception e) {}
            if (bw != null)
                try { bw.close(); } catch (Exception e) {}
        }
    }
}
