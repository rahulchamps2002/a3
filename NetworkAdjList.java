package a3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;


/**
 * CS146 Assignment 3 Node class 
 * This class is used for undirected graphs represented as adjacency lists
 * @author andreopo
 *
 * Name: Rahul Champaneria
 * Student ID: 017313296
 */

public class NetworkAdjList {

	//Node list of adjacencyList
	private static Node[] adjacencyList;

	//a function to create an Adjacency List
	public static void createAdjacencyList() {
		// Ensure the array size is large enough for the highest node index
		adjacencyList = new Node[5000];
		//create a HashSet to store nodes that are connected
		Set<Integer> nodeSet = new HashSet<>();

		//using a file reader to read through the 3980.edges and utilize the file
		try (BufferedReader br = new BufferedReader(new FileReader("3980.edges"))) {
			//intializing a string called line
			String line;
			//while loop that takes the string line and reads a part of the file 3980.edges and checks if it is null
			while ((line = br.readLine()) != null) {
				//splitting the string line and putting them into a String array called parts
				String[] parts = line.split(" ");
				//converting the parts strings into integers
				int node1 = Integer.parseInt(parts[0]);
				int node2 = Integer.parseInt(parts[1]);

				//adding the converted parts to the Node HashSet
				nodeSet.add(node1);
				nodeSet.add(node2);

				//calling the addEdges function twice since it is an undirected graph
				addEdges(node1, node2);
				addEdges(node2, node1);
			}

			// Initialize the nodes in the adjacency list
			for (int nodeID : nodeSet) {
				if (adjacencyList[nodeID] == null) {
					adjacencyList[nodeID] = new Node(nodeID);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	//a function to add nodes to an adjacency list
	private static void addEdges(int from, int to){
		Node newNode = new Node(to);
		newNode.setNext(adjacencyList[from]);
		adjacencyList[from] = newNode;
	}

	//this function checks whether or not two nodes are "friends" (neighbors)
	public static boolean areFriends(int A, int B) {
		Node node = adjacencyList[A];
		while (node != null) {
			if (node.getName() == B) {
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	public static void BFStraversal(int start) {
		/**
		 * Not needed for now
		 */
	}

	public static void main(String[] args) {
		
		/**
		 * These test cases assume the file 3980.edges was used
		 */
		createAdjacencyList();

		System.out.println("Testing...");
		assertEquals(areFriends(4038, 4014), true);
		System.out.println("1 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(3982, 4037), true);
		System.out.println("2 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 4017), true);
		System.out.println("3 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 1), false);
		System.out.println("4 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(1, 4030), false);
		System.out.println("5 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4003, 3980), true);
		System.out.println("6 of 7");
		
		System.out.println("Testing...");
		assertEquals(areFriends(3985, 4038), false);
		System.out.println("7 of 7");
		System.out.println("Success!");
	}

}
