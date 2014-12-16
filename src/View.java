import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class View {

    /**
     * column names for our table
     */
    private static Object[] columnNames = new Object[]{"Time", "0", "1", "2", "3", "4", "5"};

    /**
     * Constructor
     * @param time number of history times
     * @param nodes nodes to be extracted information from
     */
    public View(int time, final List<Node> nodes) {

        /**
         * setting up table
         */
        JTable jTable = new JTable(getTableData(time, nodes), columnNames);
        final JTextField routeText = new JTextField(30);

        /**
         * on click of a table record, fetch history of how we got there and
         * append it in routeText
         */
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable)e.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                routeText.setText(
                        History.getRouteStr(Nodes.findById(nodes, column - 1), row)
                );
            }
        });

        JFrame frame = new JFrame("History");

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 200));
        JScrollPane tableContainer = new JScrollPane(jTable);
        panel.add(tableContainer, BorderLayout.CENTER);

        JPanel rpanel = new JPanel();
        rpanel.setLayout(new BorderLayout());
        rpanel.setName("Route");
        rpanel.add(routeText, BorderLayout.CENTER);

        //panel.add(routeText, BorderLayout.CENTER);
        containerPanel.add(panel);
        containerPanel.add(rpanel);

        frame.getContentPane().add(containerPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Goes through nodes and fetches information of their history
     * @param rows number of rows
     * @param nodes list of nodes
     * @return 2d array of strings representing future table values
     */
    public String[][] getTableData(int rows, List<Node> nodes) {
        String[][] tableData = new String[rows][7];

        for (int i = 0; i < rows; i++) {
            tableData[i][0] = Integer.toString(i);

            for(Node node: nodes) {
                String str = "";
                Integer value = node.getValue(i);
                if(value == null) str = "-";
                else {
                    List<?> pNodes = node.getPNodes()[i];
                    if (pNodes == null) str = "-";
                    else {
                        str += value + "(";
                        for (Object pNode : pNodes) {
                                str += ((Node)pNode).id + ", ";
                        }
                        str = str.substring(0,str.lastIndexOf(","));
                        str += ")";
                    }
                }
                tableData[i][node.id + 1] = str;
            }
        }
        return tableData;
    }
}