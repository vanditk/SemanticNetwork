/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diabeticsemanticnetwork;

/**
 *
 * @author vandit
 */
public class Edge {
    private Node from;
    private Node to;
    private String label;

    
    public Edge(){from = null;to = null;label = new String();}
    public Edge(Node tempFrom,String tempLabel,Node tempTo){from = tempFrom;to = tempTo; label = tempLabel;}
            
    /**
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Node from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Node to) {
        this.to = to;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
}
