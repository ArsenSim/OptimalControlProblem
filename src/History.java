import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * History of jumping events
 */
public class History {

    /**
     * Total number of times we will move
     */
    public final int time;

    /**
     * Nodes that will participate in history events
     */
    private List<Node> nodes;

    public History(int time) {
        this.time = time;
    }

    /**
     * Iterate over nodes for *time* times and try to jump each of them
     */
    public void iterate() {
        nodes = Nodes.arrayList(time);

        // initializing step #0
        Node nodeZero = Nodes.findById(nodes, 0);
        nodeZero.setValue(0, 0);
        // performing other steps
        for (int t = 1; t < time; t++) {
            for (Node node : nodes) {
                jumpNode(node, t);
            }
        }
    }

    /**
     * Calculates from which node you can jump to desired node considering time of jump
     * return node(s), from which we jump to here with value(s)
     * if at this point of time we cant jump to this node, return null
     */
    public void jumpNode(Node node, int t) {
        TreeMap<Integer, List<Node>> totals = new TreeMap<Integer, List<Node>>();
        for (Map.Entry<Node, Cost> prec : node.getPrecedent().entrySet()) {
            Node precNode = prec.getKey();
            Cost cost = prec.getValue();
            // if the value of precedent node == null, then we can't jump from it
            if (precNode.getValue(t - 1) != null) {
                // the formula
                int val = precNode.getValue(t - 1) + cost.calc(t-1);
                //adding values to TreeMap, it wil sort them automatically
                if (totals.containsKey(val)) {
                    List<Node> l = totals.get(val);
                    l.add(precNode);
                    totals.put(val, l);
                } else {
                    List<Node> l = new ArrayList<Node>();
                    l.add(precNode);
                    totals.put(val, l);
                }
            }
        }
        if (totals.isEmpty()) {
            node.setValue(t, null);
            node.setPNodes(t, null);
        } else {
            // TreeMap sorts by keys, since our keys are node.currentValue, the first entry will have min
            node.setValue(t, totals.firstEntry().getKey());
            node.setPNodes(t, totals.firstEntry().getValue());
        }
    }

    /**
     * Get String showing the route to desired node at a point of time
     * @param node destination
     * @param time time of destination
     * @return String route
     */
    public static String getRouteStr(Node node, int time) {
        String str = "";
        str += node.id + "(" + node.getValue(time) + ") <--- ";
        List<?> nodes = node.getPNodes(time--);
        do {
            for (Object pNode : nodes) {
                Node n = ((Node)pNode);
                str += n.id + "(" + n.getValue(time) + "), ";
                str = str.substring(0, str.lastIndexOf(","));
                nodes = n.getPNodes(time--);
            }
            str += " <--- ";
        } while (nodes != null);
        str = str.substring(0, str.lastIndexOf(" <---"));
        return str;
    }

    public List<Node> getNodesList() {
        return nodes;
    }
}
