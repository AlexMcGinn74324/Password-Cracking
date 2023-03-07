import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PasswordStuff {
//================================================Main
    public static void main(String[] args) throws FileNotFoundException {
        DirectedSparseGraph<Integer, String> g = new DirectedSparseGraph<>();
        Map<Integer, String> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        createGraph(g);
        System.out.println(shortestPass(g));

    }
//=====================================================================================ShortestPass
    public static String shortestPass(DirectedSparseGraph<Integer, String> g){
//        Set<Integer> toDo = new HashSet<>();
        List<Integer> vertices = new ArrayList<>();
        vertices = g.getVertices().stream().toList();
        Set<Integer> done = new HashSet<>();

        String toRet = "";

        while((g.getVertexCount() != 0)) {  //for for each vertex
            for (int i = 0; i < vertices.size(); i++) {
                if(!done.contains(vertices.get(i)) && g.inDegree(vertices.get(i)) == 0){    //if it's not a finished number and has indegree 0
                    toRet += vertices.get(i);
                    System.out.println(vertices.get(i));
                    g.removeVertex(vertices.get(i));
                    done.add(vertices.get(i));
                    break;
                }

            }
        }

        return toRet;
    }
//=======================================================================================================CreateGraph

    public static void createGraph(DirectedSparseGraph<Integer, String> g) throws FileNotFoundException {
        String filename = "keyValues.txt";
        Scanner in = new Scanner(new File(filename));
        while(in.hasNextLine()){
            String line = in.nextLine();
            char nums[] = line.toCharArray();
            for (int i = 0; i < 2; i++) {
                String edge = "" + nums[i] + nums[i+1];
//                System.out.println(edge);
                g.addEdge(edge, Character.getNumericValue(nums[i]), Character.getNumericValue(nums[i+1]));
            }
        }
    }

}
