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

    public void testFunctions() {
//        System.out.println("david is a diabetic: "+isA(nodes.get("david"),nodes.get("diabetics")));
//        System.out.println("snickers ako candy: "+ aKindOf(nodes.get("snickers"), nodes.get("candy")));
//        System.out.println("snickers contains sugar: " + contains(nodes.get("snickers"), nodes.get("sugar")));
//        //System.out.println("diabetics shouldAvoid sugar: "+ shouldAvoid(nodes.get("diabetics"), nodes.get("sugar")));
//        //System.out.println("david shouldAvoid sugar: "+ shouldAvoid(nodes.get("david"), nodes.get("sugar")));
//        System.out.println("david shouldAvoid candy: "+ shouldAvoid(nodes.get("david"), nodes.get("candy")));
//        System.out.println("david shouldAvoid snickers: "+ shouldAvoid(nodes.get("david"), nodes.get("snickers")));
//        System.out.println("diabetics shouldAvoid snickers: "+ shouldAvoid(nodes.get("diabetics"), nodes.get("snickers")));
//        System.out.println("sugar shouldAvoid snickers: "+ shouldAvoid(nodes.get("sugar"), nodes.get("snickers")));
//        System.out.println("diabetics shouldAvoid sugar: "+ shouldAvoid(nodes.get("sugar"), nodes.get("snickers")));
    }

    //  x isA y ?
    public boolean isA(Node node1, Node node2,boolean recursive) {

        boolean node1isnode2 = false;
        if (node1 == null || node2 == null || node1.getEdges() == null) {
            node1isnode2 = false;
        } else {

            ArrayList<Edge> edges = node1.getEdges();
            if (edges != null) {
                for (Edge tempEdge : edges) {
                    if (tempEdge.getLabel().equals("isa")) {
                        if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                            node1isnode2 = true;
                            break;
                        } else if(recursive){
                            node1isnode2 = isA(tempEdge.getTo(), node2,recursive);
                        }
                    } else {
                        node1isnode2 = false;
                    }
                }
            }
        }
        return node1isnode2;
    }

    public boolean aKindOf(Node node1, Node node2,boolean recursive) {
        boolean isAKindOf = false;
        if (node1 == null || node2 == null || node1.getEdges() == null) {
            isAKindOf = false;
        } else {
            ArrayList<Edge> edges = node1.getEdges();
            if (edges != null) {
                for (Edge tempEdge : edges) {
                    if (tempEdge.getLabel().equals("ako")) {
                        if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                            isAKindOf = true;
                            break;
                        } else if(recursive){
                            isAKindOf = isA(tempEdge.getTo(), node2,recursive);
                        }
                    } else {
                        isAKindOf = false;
                    }
                }
            }
        }
        return isAKindOf;
    }

    public boolean shouldAvoid(Node node1, Node node2,boolean recursive) {

        boolean xshouldAvoidy = false;
        if (node1 == null || node2 == null) {
            xshouldAvoidy = false;
        } else {
            ArrayList<Edge> edges = node1.getEdges();
            if (edges != null) {
                for (Edge tempEdge : edges) {
                    Node tempNode = null;


                    if (tempEdge == null) {
                        xshouldAvoidy = false;

                    } else {
                        if (tempEdge.getLabel().equals("shouldAvoid")) {
                            if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                                xshouldAvoidy = true;
                                break;
                            } else  if(recursive) {
                                //return shouldAvoid(tempEdge.getTo(), node2);
                                //xshouldAvoidy = contains(node2, tempEdge.getTo()) || aKindOf(node2, tempEdge.getTo());
                                xshouldAvoidy = contains(node2, tempEdge.getTo(),recursive);
                            }
                        } else  if(recursive){
                            tempNode = tempEdge.getTo();
                            if (tempEdge.getLabel().equals("isa")) {
                                xshouldAvoidy = shouldAvoid(tempNode, node2,recursive);
                            } else {

                                xshouldAvoidy = false;
                            }
                        }
                    }
                }
            } else {
                xshouldAvoidy = false;
            }
        }
        return xshouldAvoidy;
    }

    public boolean contains(Node node1, Node node2,boolean recursive) {
        boolean doesContain = false;
        if (node1 == null || node2 == null || node1.getEdges() == null) {
            doesContain = false;
        } else {
            ArrayList<Edge> edges = node1.getEdges();
            if (edges != null) {
                for (Edge tempEdge : edges) {
                    Node tempNode = null;

                    if ("contains".equals(tempEdge.getLabel())) {
                        if (tempEdge.getTo().getValue().equals(node2.getValue())) {
                            doesContain = true;
                            break;
                        } else  if(recursive){
                            doesContain = contains(tempEdge.getTo(), node2,recursive);
                        }
                    } else  if(recursive){
                        if ("ako".equals(tempEdge.getLabel())) {
                            tempNode = tempEdge.getTo();
                            doesContain = contains(tempNode, node2,recursive);
                        } else {
                            doesContain = false;
                        }
                    }
                }
            }

        }
        return doesContain;
    }
}
