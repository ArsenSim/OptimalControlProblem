import java.util.ArrayList;
import java.util.List;

/**
 * class with static functions to work with node data
 */
public class Nodes {

    /**
     * Private constructor, so we can't create any instance of this class
     */
    private Nodes() {}

    /**
     * Finds specific node in a list of nodes
     * @param nodes list of nodes to search
     * @param id id of desired node
     * @return node, null if not found
     */
    public static Node findById(List<Node> nodes, int id) {
        for (Node node : nodes) {
            if (node.id == id) return node;
        }
        return null;
    }

    /**
     * Generates an array list of nodes with relations
     * and cost functions for each edge
     * (for the purpose of showing an example)
     */
    public static List<Node> arrayList(int time) {
        List<Node> nodes = new ArrayList<Node>();

        //generating 6 nodes
        for (int i = 0; i < 6; i++) {
            nodes.add(new Node(i, time));
        }

        //adding relations to those nodes
        nodes.get(1).addPrecedent(nodes.get(0), new Cost("1"));
        nodes.get(1).addPrecedent(nodes.get(3), new Cost("2t"));

        nodes.get(2).addPrecedent(nodes.get(1), new Cost("t"));

        nodes.get(3).addPrecedent(nodes.get(0), new Cost("1"));
        nodes.get(3).addPrecedent(nodes.get(2), new Cost("2t"));

        nodes.get(4).addPrecedent(nodes.get(2), new Cost("t"));
        nodes.get(4).addPrecedent(nodes.get(3), new Cost("2t+2"));

        nodes.get(5).addPrecedent(nodes.get(1), new Cost("t"));
        nodes.get(5).addPrecedent(nodes.get(2), new Cost("1"));
        nodes.get(5).addPrecedent(nodes.get(4), new Cost("2t+1"));

        return nodes;
    }
}
