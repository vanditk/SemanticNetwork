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
        Map<String, Node> nodes = network.getNodes();

//
//        String query = "X A Y";        
//        System.out.println("Your Query: "+query);
//        String[] queryParams = query.split(" ");
//        String val1 = queryParams[0].trim();
//        String relationQuery = queryParams[1].trim();
//        String val2 = queryParams[2].trim();
//        
        String function = args[0].trim();
        boolean recursive = function.equalsIgnoreCase("value");
        String val1 = args[1].trim();
        String relationQuery = args[2].trim();
        String val2 = args[3].trim();
        System.out.println("Your Query: " + val1 + " " + relationQuery + " " + val2);
        ArrayList<ArrayList<String>> combinations = new ArrayList<ArrayList<String>>();
        ArrayList<String> keySet = new ArrayList<String>(network.getNodes().keySet());
        ArrayList<String> relations = new ArrayList<String>();
        boolean bindings[] = {false,false,false};
        
        network.testFunctions();
        if (isVariable(relationQuery)) {
            bindings[1] = true;
            relations.add("isA");
            relations.add("ako");
            relations.add("contains");
            relations.add("shouldAvoid");
        } else {
            relations.add(relationQuery);

        }


        if (isVariable(val1) && isVariable(val2)) {
            bindings[0]= true;
            bindings[2] = true;
            combinations = computeCombinations(keySet, keySet);
        } else if (isVariable(val1) && !isVariable(val2)) {
            bindings[0]= true;
            
            ArrayList<String> newArray = new ArrayList<String>();
            newArray.add(val2);
            combinations = computeCombinations(keySet, newArray);

        } else if (!isVariable(val1) && isVariable(val2)) {
            
            bindings[2] = true;
            ArrayList<String> newArray = new ArrayList<String>();
            newArray.add(val1);
            combinations = computeCombinations(newArray, keySet);
        } else if (!isVariable(val1) && !isVariable(val2)) {
            bindings[0]= false;
            bindings[2] = false;
            ArrayList<String> combi = new ArrayList<String>();
            combi.add(val1);
            combi.add(val2);
            combinations.add(combi);
        }
        boolean isTrue = false;
        for (String relation : relations) {

            if (relation.equals("contains")) {

                for (ArrayList<String> combi : combinations) {
                    if (network.contains(nodes.get(combi.get(0)), nodes.get(combi.get(1)), recursive)) {
                        if(bindings[0]){System.out.println(val1+" = "+combi.get(0));}
                        if(bindings[1]){System.out.println(relationQuery+" = "+relation);}
                        if(bindings[2]){System.out.println(val2+" = "+combi.get(1));}
                        //System.out.println(combi.get(0) + " contains " + combi.get(1));
                        isTrue = true;
                    }
                }
            } else if (relation.equals("isA")) {
                for (ArrayList<String> combi : combinations) {
                    if (network.isA(nodes.get(combi.get(0)), nodes.get(combi.get(1)), recursive)) {
                        if(bindings[0]){System.out.println(val1+" = "+combi.get(0));}
                        if(bindings[1]){System.out.println(relationQuery+" = "+relation);}
                        if(bindings[2]){System.out.println(val2+" = "+combi.get(1));}
                        isTrue = true;
                    }
                }
            } else if (relation.equals("ako")) {
                for (ArrayList<String> combi : combinations) {
                    if (network.aKindOf(nodes.get(combi.get(0)), nodes.get(combi.get(1)), recursive)) {
                        if(bindings[0]){System.out.println(val1+" = "+combi.get(0));}
                        if(bindings[1]){System.out.println(relationQuery+" = "+relation);}
                        if(bindings[2]){System.out.println(val2+" = "+combi.get(1));}
                        isTrue = true;
                    }
                }
            } else if (relation.equals("shouldAvoid")) {
                for (ArrayList<String> combi : combinations) {
                    if (network.shouldAvoid(nodes.get(combi.get(0)), nodes.get(combi.get(1)), recursive)) {
                        if(bindings[0]){System.out.println(val1+" = "+combi.get(0));}
                        if(bindings[1]){System.out.println(relationQuery+" = "+relation);}
                        if(bindings[2]){System.out.println(val2+" = "+combi.get(1));}
                        isTrue = true;
                    }
                }
            }

        }

        if (!isTrue) {
            System.out.println(false);
        }

        //System.out.println("combinations: " + combinations);

    }

    private static boolean isVariable(String var1) {
        boolean isVar = false;
        char x = var1.charAt(0);
        if ('A' <= x && 'Z' >= x) {
            isVar = true;
        }
        return isVar;
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
                ArrayList<Edge> edges = existingFromNode.getEdges();
                if (edges == null) {
                    edges = new ArrayList<Edge>();
                }
                edges.add(tempEdge);
                existingFromNode.setEdges(edges);
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
        //System.out.println(" Network prepared");
        return myNetwork;


    }

    private static ArrayList<ArrayList<String>> computeCombinations(ArrayList<String> keySet, ArrayList<String> keySet1) {

        ArrayList<ArrayList<String>> combinations = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < keySet.size(); i++) {
            for (int j = 0; j < keySet1.size(); j++) {
                ArrayList<String> combi = new ArrayList<String>();
                if (keySet.get(i).equals(keySet1.get(j))) {
                    continue;
                } else {
                    combi.add(keySet.get(i));
                    combi.add(keySet1.get(j));
                    combinations.add(combi);

                }
            }
        }


        return combinations;

    }
}
