/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diabeticsemanticnetwork;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author vandit
 */
public class DiabeticSemanticNetwork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SemanticNetwork network = createNetwork();
        /*
         * String query = "david shouldAvoid What"; String[] params =
         * query.split(" ");
         *
         */
        String a = "david";
        String b = "shouldAvoid";
        String c = "What";
        Map<String, Node> nodes = network.getNodes();
        Node node1 = nodes.get(a);
        ArrayList<Node> answer = new ArrayList<Node>();
        Edge edge = new Edge(b);
        Node node2 = nodes.get(c);
        //answer = network.traverse(node1, edge, node2);
        network.testFunctions();

    }

    public static SemanticNetwork createNetwork() {


        SemanticNetwork myNetwork = new SemanticNetwork();
        Map<String, Node> myNodes = myNetwork.getNodes();
        try {
            InputStream inputFile = DiabeticSemanticNetwork.class.getResourceAsStream("network.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFile));
            String rawLine = br.readLine();
            while (rawLine != null) {

                String[] parts = rawLine.split(",");

                String from, to, edgeLabel;
                from = parts[0].trim();
                to = parts[2].trim();
                edgeLabel = parts[1].trim();

                //

                Node existingToNode = myNodes.get(to);
                Node existingFromNode = myNodes.get(from);
                Edge tempEdge = new Edge(edgeLabel);
                if (null == existingToNode) {
                    existingToNode = new Node(to);

                }
                if (null == existingFromNode) {
                    existingFromNode = new Node(from);

                }
                tempEdge.setFrom(existingFromNode);
                existingFromNode.setEdge(tempEdge);
                tempEdge.setTo(existingToNode);
                myNetwork.addEdgeToNetwork(tempEdge);
                myNodes.put(to, existingToNode);
                myNodes.put(from, existingFromNode);


                rawLine = br.readLine();
            }
            myNetwork.setNodes(myNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" Network prepared");
        return myNetwork;


    }
}
