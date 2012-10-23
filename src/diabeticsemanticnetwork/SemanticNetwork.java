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

    private Map<String, Node> nodes = new HashMap<String, Node>();
    //not needed.
    private Map<String, ArrayList<Edge>> edges = new HashMap<String, ArrayList<Edge>>();

    /**
     * @return the nodes
     */
    public Map<String, Node> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Map<String, Node> nodes) {
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
    public void setEdges(Map<String, ArrayList<Edge>> edges) {
        this.edges = edges;
    }

    public void addEdgeToNetwork(Edge e) {
        ArrayList<Edge> edgesForLabel = edges.get(e.getLabel());
        if (edgesForLabel == null) {
            edgesForLabel = new ArrayList<Edge>();
        }
        edgesForLabel.add(e);
        edges.remove(e.getLabel());
        edges.put(e.getLabel(), edgesForLabel);
    }
    public void testFunctions()
    {
        System.out.println("david is a diabetic: "+isA(nodes.get("david"),nodes.get("diabetics")));
        System.out.println("snickers ako candy: "+ aKindOf(nodes.get("snickers"), nodes.get("candy")));
        System.out.println("snickers contains sugar: "+ contains(nodes.get("snickers"), nodes.get("sugar")));
        //System.out.println("diabetics shouldAvoid sugar: "+ shouldAvoid(nodes.get("diabetics"), nodes.get("sugar")));
        //System.out.println("david shouldAvoid sugar: "+ shouldAvoid(nodes.get("david"), nodes.get("sugar")));
        System.out.println("david shouldAvoid candy: "+ shouldAvoid(nodes.get("david"), nodes.get("candy")));
        System.out.println("david shouldAvoid snickers: "+ shouldAvoid(nodes.get("david"), nodes.get("snickers")));
        System.out.println("diabetics shouldAvoid snickers: "+ shouldAvoid(nodes.get("diabetics"), nodes.get("snickers")));
    }
    
    //  x isA y ?
    private boolean isA(Node x, Node y) {
        Edge tempEdge = x.getEdge();
        if (tempEdge.getLabel().equals("isa")) {
            if (tempEdge.getTo().getValue().equals(y.getValue())) {
                return true;
            } else {
                return isA(tempEdge.getTo(), y);
            }
        } else {
            return false;
        }
    }

    private boolean aKindOf(Node node1, Node node2) {
        Edge tempEdge = node1.getEdge();
        if (tempEdge.getLabel().equals("ako")) {
            if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                return true;
            } else {
                return isA(tempEdge.getTo(), node2);
            }
        } else {
            return false;
        }
    }

    private boolean shouldAvoid(Node node1, Node node2) {

        Edge tempEdge = node1.getEdge();
        Node tempNode = null;
        if (tempEdge == null) {
            return contains(node2,node1);
            
        } else {
            if (tempEdge.getLabel().equals("shouldAvoid")) {
                if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                    return true;
                } else {
                    return shouldAvoid(tempEdge.getTo(), node2);
                }
            } else {
                tempNode = tempEdge.getTo();
                if (tempEdge.getLabel().equals("isa")) {
                    return shouldAvoid(tempNode, node2);
                } else {
                    //write condition for contains.
                    return false;
                }
            }
        }
    }

    private boolean contains(Node node1, Node node2) {
        Edge tempEdge = node1.getEdge();
        Node tempNode = null;
        if (tempEdge.getLabel().equals("contains")) {
            if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                return true;
            } else {
                return contains(tempEdge.getTo(), node2);
            }
        } else {
            if (tempEdge.getLabel().equals("ako")) {
                tempNode = tempEdge.getTo();
                return contains(tempNode, node2);
            } else {
                return false;
            }
        }


    }
}
