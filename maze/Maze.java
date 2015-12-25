package maze;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

import java.io.*;
import java.util.ArrayList;


public class Maze implements GraphInterface {

    private ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>();
    private String fileName;
    private int height, width;
    private MBox[][] boxes;


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public VertexInterface getVertex(int x, int y) {
        for(VertexInterface v : vertices) {
            if(v.getX()==x && v.getY()==y)
                return v;
        }
        return null;
    }
    public final ArrayList<VertexInterface> getAllVertices() {
        try {
            FileReader fin = new FileReader(fileName);
            BufferedReader bin = new BufferedReader(fin);
            String str = bin.readLine();
            int x = 0, y = 0;
            while(str != null) {
                char[] ch = str.toCharArray();
                for(char c : ch) {
                    switch(c) {
                        case('A'):
                            ABox a = new ABox(x,y,this);
                            vertices.add(a);
                            break;
                        case('E'):
                            EBox e = new EBox(x,y,this);
                            vertices.add(e);
                            break;
                        case('D'):
                            DBox d = new DBox(x,y,this);
                            vertices.add(d);
                            break;
                    }
                    y++;
                }
                str = bin.readLine();
                x++;
            }
            bin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }

    @Override
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        int x = vertex.getX() - 1;
        int y = vertex.getY() - 1;
        ArrayList<VertexInterface> l = new ArrayList<VertexInterface>();
        while(x < vertex.getX()+1) {
            while(y < vertex.getY()+1) {
                VertexInterface v = getVertex(x,y);
                if(v != null) {
                    l.add(v);
                }
                y++;
            }
            y = y -2;
            x++;
        }
        l.remove(vertex);
        return l;
    }

    @Override
    public int getWeight(VertexInterface src, VertexInterface dst) {
        ArrayList<VertexInterface> l = getSuccessors(src);
        if(l.contains(dst))
            return 1;
        else
            return 0;
    }

    public final void initFromTextFile(String fileName) throws MazeReadingException {
        this.fileName = fileName;
        boxes = new MBox[height][width];
        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++){
                boxes[i][j] = new WBox(i,j,this);
            }
        }
        ArrayList<VertexInterface> vertices = getAllVertices();
        for(VertexInterface v : vertices) {
            int x = v.getX();
            int y = v.getY();
            boxes[x][y] = (MBox) v;
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
