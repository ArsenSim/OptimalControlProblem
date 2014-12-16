import java.util.*;

/**
 * Represents nodes in a directed graph, with each edge having its own cost function
 */
public class Node {

    /**
     * node id
     */
    public final int id;

    /**
     * precedent nodes, representing relationship in directed graph
     */
    private Map<Node, Cost> precedent;

    /**
     * records of value changes in current node. values[i] - value of node acquired at time i
     */
    private Integer[] values;

    /**
     * records of precedent node, from which we jumped at current node at any point of time
     * pNodes[i] - precedent nodes acquired at time i
     */
    private List<?>[] pNodes;

    /**
     * Constructor
     * @param id node id
     * @param dimension the size of ArrayLists values, pNodes
     */
    public Node(int id, int dimension) {
        this.id = id;
        this.precedent = new HashMap<Node, Cost>();
        this.values = new Integer[dimension];
        this.pNodes = new List<?>[dimension];
    }

    /**
     * @return string representing object information
     */
    @Override
    public String toString() {
        String str = "[";
        for (List<?> nodes : pNodes) {
            if (nodes != null) {
                for (Object node : nodes) {
                    str += ((Node)node).id + ", ";
                }
            } else {
                str += "null, ";
            }
        }
        str = str.substring(0, str.lastIndexOf(","));
        str += "]";

        return "Node{" +
                "id=" + id +
                ", values=" + values +
                ", pNodes=" + str +
                "}";
    }

    /**
     * Adding preceding node. Basically links this node to precedent and
     * sets cost function for the created edge
     * @param node - reference of precedent node
     * @param cost - cost function of going to this node
     */
    public void addPrecedent(Node node, Cost cost) {
        precedent.put(node, cost);
    }

    /**
     * Fetches a record of value, that the node had at a specific point of time
     * @param time time at which node had value
     * @return value
     */
    public Integer getValue(int time) {
        return values[time];
    }

    /**
     * Sets a record of value, that the node had at a specific point of time
     * @param time time at which node has value
     * @param value value at which node has value
     */
    public void setValue(int time, Integer value) {
        values[time] = value;
    }

    /**
     * Sets precedent node(s), from which we jumped at current node at
     * a specific point of time
     * @param time time at which node had been jumped from precedent node(s)
     * @param nodes list of precedent node(s), from which we jumped at current one
     */
    public void setPNodes(int time, List<Node> nodes) {
        pNodes[time] = nodes;
    }

    public Map<Node, Cost> getPrecedent() {
        return precedent;
    }

    public List<?>[] getPNodes() {
        return pNodes;
    }

    public List<?> getPNodes(int time) {
        return pNodes[time];
    }
}
