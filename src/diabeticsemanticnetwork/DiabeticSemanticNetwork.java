/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diabeticsemanticnetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        
        
    }
    
    public static SemanticNetwork createNetwork()
    {
        
        ArrayList<SemanticNetwork> networks = new ArrayList<SemanticNetwork>();
        
        SemanticNetwork myNetwork = new SemanticNetwork();
        Map<String,Node> myNodes = myNetwork.getNodes();
        try {
            InputStream inputFile = DiabeticSemanticNetwork.class.getResourceAsStream("network.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFile));
            String rawLine = br.readLine();
            while (rawLine != null) {

                String[] parts = rawLine.split(",");
                Node tempFrom = new Node(parts[0].trim());
                Node tempTo = new Node(parts[2].trim());
                Edge tempEdge = new Edge(tempFrom,parts[1].trim(),tempTo);
                
                myNodes.put(tempFrom.getValue(), tempFrom);
                myNodes.put(tempTo.getValue(), tempTo);
                //Map<String, ArrayList<Edge>> myEdges = myNetwork.getEdges();
                
                rawLine = br.readLine();
            }
            myNetwork.setNodes(myNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("total number of paths : " + roads.size());
        return networks;
    
    
    }
    
}
