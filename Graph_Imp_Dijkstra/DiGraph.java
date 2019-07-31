package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraphInterface {
    HashMap<String, DiGraphV> mapV = new HashMap<>();
    HashMap<Long, DiGraphE> mapE = new HashMap<>();
    HashMap<DiGraphV, List<DiGraphE>> mapG = new HashMap<>();

    public  ShortestPathInfo[] shortestPath(String label) {
    	genGraph();
        DiGraphV sourceV = mapV.get(label);

        //customize the comparator for PQ
        Comparator<node> compNode = new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                if (o1.dist > o2.dist) return 1;
                else if (o1.dist == o2.dist) return 0;
                else return -1;
            }
        };

        //create a priority queue to store the distance-unknown vertex
        PriorityQueue<node> pq = new PriorityQueue<>(mapV.size(), compNode);

        //stores shortest distance from root to every vertex
        HashMap<DiGraphV,Long> distance = new HashMap<>();

        //put source node in the pq
        node sourceN = new node(sourceV, (long) 0);
        pq.add(sourceN);

        //put source node in distance table with a distance of 0;
        distance.put(sourceV, (long) 0);

        // is pq is empty find adjacent nodes of the one put in distance table

//        add adjacent nodes to source node into pq
        for (DiGraphE edge : mapG.get(sourceV)) {
        	DiGraphV v = mapV.get(edge.dLabel);
        	node n = new node(v, edge.weight);
        	pq.add(n);
        }

//        loop while pq is not empty
        while (!pq.isEmpty()) {
        	node minNode = pq.poll();
        	if (minNode.dist < distance.getOrDefault(minNode.vertex, Long.MAX_VALUE)) {
        		distance.put(minNode.vertex, minNode.dist);
        		for (DiGraphE edge : mapG.get(minNode.vertex)) {
            		DiGraphV v = mapV.get(edge.dLabel);
                	if (distance.containsKey(v)) continue;
            		long totalWeight = edge.weight + minNode.dist;
                	node n = new node(v, totalWeight);
                	pq.add(n);
        		}
        	}
        }

        //put destination vertex and weight into ShortestPathInfo[]
        int n = mapV.keySet().size();
        int i = 0;
        ShortestPathInfo[] infos = new ShortestPathInfo[n];
        for (DiGraphV vertex : distance.keySet()) {
            infos[i++] = new ShortestPathInfo(vertex.label, distance.get(vertex));
        }
        for (DiGraphV v : mapV.values()) {
        	if (!distance.containsKey(v)) infos[i++] = new ShortestPathInfo(v.label, -1);
        }
        return infos;
    }

    // rest of your code to implement the various operations


    public DiGraph() { // default constructor
        // explicitly include this
        // we need to have the default constructor
        // if you then write others, this one will still be there
        // generate map using objects DiGraphE and DiGraphV;

    }

    public void genGraph() {
        for (DiGraphV vet : mapV.values()) {
            List<DiGraphE> edgList = new ArrayList<>();
            for (DiGraphE edg : mapE.values()) {
                if (vet.label == edg.sLabel) edgList.add(edg);
            }
            mapG.put(vet, edgList);
        }
    }

    @Override
    public boolean addNode(long idNum, String label) {
        DiGraphV vertex = new DiGraphV(idNum, label);
        if (mapV.containsKey(label) || label == null) return false;
        for (DiGraphV v : mapV.values()) {
            if (v.idNum == idNum) return false;
        }
        mapV.put(label, vertex);
        return true;
    }

    @Override
// didn't figure out how to set default parameter in Java, so I wrote 4 functions for addEdge:
//with all the five parameters
//without weight
//without eLabel
//without neither weight nor eLabel


// addEdge with all the 5 parameters
    public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
        DiGraphE edge = new DiGraphE(idNum, sLabel, dLabel, weight, eLabel);
        if (mapE.containsKey(idNum) || idNum < 0) return false;
        boolean testS = false, testD = false, testE = true;
        for (DiGraphV v : mapV.values()) {
            if (v.label == sLabel) {
                testS = true;
                break;
            }
        }
        for (DiGraphV v : mapV.values()) {
            if (v.label == dLabel) {
                testD = true;
                break;
            }
        }
        for (DiGraphE e : mapE.values()) {
            if (e.sLabel == sLabel && e.dLabel == dLabel) {
                testE = false;
                break;
            }
        }
        if (testS && testD && testE) {
            mapE.put(idNum, edge);
            return true;
        }
        return false;
    }


    //addEdge without weight
    public boolean addEdge(long idNum, String sLabel, String dLabel, String eLabel) {
        DiGraphE edge = new DiGraphE(idNum, sLabel, dLabel, eLabel);
        if (mapE.containsKey(idNum) || idNum < 0) return false;
        boolean testS = false, testD = false, testE = true;
        for (DiGraphV v : mapV.values()) {
            if (v.label == sLabel) {
                testS = true;
                break;
            }
        }
        for (DiGraphV v : mapV.values()) {
            if (v.label == dLabel) {
                testD = true;
                break;
            }
        }
        for (DiGraphE e : mapE.values()) {
            if (e.sLabel == sLabel && e.dLabel == dLabel) {
                testE = false;
                break;
            }
        }
        if (testS && testD && testE) {
            mapE.put(idNum, edge);
            return true;
        }
        return false;
    }

    //addEdge without edge label
    public boolean addEdge(long idNum, String sLabel, String dLabel, long weight) {
        DiGraphE edge = new DiGraphE(idNum, sLabel, dLabel, weight);
        if (mapE.containsKey(idNum) || idNum < 0) return false;
        boolean testS = false, testD = false, testE = true;
        for (DiGraphV v : mapV.values()) {
            if (v.label == sLabel) {
                testS = true;
                break;
            }
        }
        for (DiGraphV v : mapV.values()) {
            if (v.label == dLabel) {
                testD = true;
                break;
            }
        }
        for (DiGraphE e : mapE.values()) {
            if (e.sLabel == sLabel && e.dLabel == dLabel) {
                testE = false;
                break;
            }
        }
        if (testS && testD && testE) {
            mapE.put(idNum, edge);
            return true;
        }
        return false;
    }

    //addEdge without neither weight nor edge label
    public boolean addEdge(long idNum, String sLabel, String dLabel) {
        DiGraphE edge = new DiGraphE(idNum, sLabel, dLabel);
        if (mapE.containsKey(idNum) || idNum < 0) return false;
        boolean testS = false, testD = false, testE = true;
        for (DiGraphV v : mapV.values()) {
            if (v.label == sLabel) {
                testS = true;
                break;
            }
        }
        for (DiGraphV v : mapV.values()) {
            if (v.label == dLabel) {
                testD = true;
                break;
            }
        }
        for (DiGraphE e : mapE.values()) {
            if (e.sLabel == sLabel && e.dLabel == dLabel) {
                testE = false;
                break;
            }
        }
        if (testS && testD && testE) {
            mapE.put(idNum, edge);
            return true;
        }
        return false;
    }


    @Override
    public boolean delNode(String label) {
        if (mapV.containsKey(label)) {
            mapV.remove(label);
            return true;
        }
        return false;
    }

    @Override
    public boolean delEdge(String sLabel, String dLabel) {
        for (DiGraphE e : mapE.values()) {
            if (e.sLabel == sLabel && e.dLabel == dLabel) {
                mapE.remove(e.idNum);
                return true;
            }
        }
        return false;
    }

    @Override
    public long numNodes() {
        return mapV.size();
    }

    @Override
    public long numEdges() {
        return mapE.size();
    }



}
