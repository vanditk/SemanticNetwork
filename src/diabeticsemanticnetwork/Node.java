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
    //not needed
    
    private Edge edge;

    
    public Node(){Value = new String();}
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
     * @return the edge
     */
    public Edge getEdge() {
        return edge;
    }

    /**
     * @param edge the edge to set
     */
    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    /**
     * @return the from
     */
    
 }
