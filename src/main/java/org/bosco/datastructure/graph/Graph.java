package org.bosco.datastructure.graph;

import java.util.List;

public interface Graph {

	public void insertVertex(Vertex x) ;
	
	public void insertEdge(Vertex u, Vertex v) ;
	
	public void deleteVertex(Vertex x) ;
	
	public void deleteEdge(Vertex u, Vertex v) ;
		
	public boolean isEmpty() ;
	
	List<Vertex> Adjacent(Vertex x) ;
	
}