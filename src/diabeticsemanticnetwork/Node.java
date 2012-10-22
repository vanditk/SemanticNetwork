/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diabeticsemanticnetwork;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vandit
 */
public class Node{
    
    private String Value;
    private Map<Edge,Node> from;
    private Map<Edge,Node> to;

    
    public Node(){Value = new String();from = new HashMap<Edge, Node>();to = new HashMap<Edge, Node>();}
    public Node(String val){Value = val;}
      
    @Override
    public boolean equals(Object x)
    {
        if (x instanceof Node)
        {
            return Value.equals(((Node)x).getValue());
            
        }
        else 
        {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.Value != null ? this.Value.hashCode() : 0);
        return hash;
    }
    
    /**
     * @return the Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(String Value) {
        this.Value = Value;
    }

    /**
     * @return the from
     */
    public Map<Edge,Node> getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Map<Edge,Node> from) {
        this.setFrom(from);
    }

    /**
     * @return the to
     */
    public Map<Edge,Node> getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Map<Edge,Node> to) {
        this.setTo(to);
    }

}
