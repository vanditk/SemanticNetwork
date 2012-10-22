/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diabeticsemanticnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vandit
 */
public class SemanticNetwork {
    
    private Map<String,Node> nodes = new HashMap<String, Node>();
   //not needed.
    private Map<String,ArrayList<Edge>> edges = new HashMap<String,ArrayList<Edge>>();

    /**
     * @return the nodes
     */
    public Map<String,Node> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Map<String,Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the edges
     */
    public Map<String, ArrayList<Edge>> getEdges() {
        return edges;
    }

    /**
     * @param edges the edges to set
     */
    public void setEdges(Map<String,ArrayList<Edge>> edges) {
        this.edges = edges;
    }
    
}
