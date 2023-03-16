import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private JFrame frame;
    private JTextField timeLimit;
    private JLabel timeLimitLabel;
    private JTextField maxProcessingTime;
    private JLabel maxProcessingTimeLabel;
    private JTextField minProcessingTime;
    private JLabel minProcessingTimeLabel;
    private JTextField maxArrivalTime;
    private JLabel maxArrivalTimeLabel;
    private JTextField minArrivalTime;
    private JLabel minArrivalTimeLabel;
    private JTextField numberOfClients;
    private JLabel numberOfClientsLabel;
    private JTextField numberOfServers;
    private JLabel numberOfServersLabel;
    private JButton start;
    private JComboBox strategyChoice;
    private JLabel startegyChoiceLabel;
    private static JTextArea textArea;
    private JScrollPane scrollBar;
    private JButton reset;

    public SimulationFrame(){
        frame = new JFrame();
        frame.setBounds(0, 0, 1055, 648);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        timeLimit = new JTextField();
        timeLimit.setBounds(271, 18, 146, 36);
        frame.getContentPane().add(timeLimit);
        timeLimit.setColumns(10);

        timeLimitLabel = new JLabel("Simulation time =");
        timeLimitLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeLimitLabel.setBounds(98, 17, 172, 38);
        frame.getContentPane().add(timeLimitLabel);

        maxProcessingTime = new JTextField();
        maxProcessingTime.setBounds(271, 64, 146, 36);
        frame.getContentPane().add(maxProcessingTime);
        maxProcessingTime.setColumns(10);

        maxProcessingTimeLabel = new JLabel("Maximum processing time =");
        maxProcessingTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        maxProcessingTimeLabel.setBounds(10, 70, 282, 29);
        frame.getContentPane().add(maxProcessingTimeLabel);

        minProcessingTime = new JTextField();
        minProcessingTime.setBounds(727, 64, 146, 36);
        frame.getContentPane().add(minProcessingTime);
        minProcessingTime.setColumns(10);

        minProcessingTimeLabel = new JLabel("Minimum processing time =");
        minProcessingTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        minProcessingTimeLabel.setBounds(467, 70, 262, 29);
        frame.getContentPane().add(minProcessingTimeLabel);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setColumns(10);
        maxArrivalTime.setBounds(271, 118, 146, 36);
        frame.getContentPane().add(maxArrivalTime);

        maxArrivalTimeLabel = new JLabel("Maximum arrival time =");
        maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        maxArrivalTimeLabel.setBounds(43, 118, 227, 29);
        frame.getContentPane().add(maxArrivalTimeLabel);

        minArrivalTime = new JTextField();
        minArrivalTime.setColumns(10);
        minArrivalTime.setBounds(727, 111, 146, 36);
        frame.getContentPane().add(minArrivalTime);

        minArrivalTimeLabel = new JLabel("Minimum arrival time =");
        minArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        minArrivalTimeLabel.setBounds(499, 118, 214, 29);
        frame.getContentPane().add(minArrivalTimeLabel);

        numberOfClients = new JTextField();
        numberOfClients.setColumns(10);
        numberOfClients.setBounds(271, 165, 146, 36);
        frame.getContentPane().add(numberOfClients);

        numberOfClientsLabel = new JLabel("Number of clients =");
        numberOfClientsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberOfClientsLabel.setBounds(79, 165, 191, 29);
        frame.getContentPane().add(numberOfClientsLabel);

        numberOfServers = new JTextField();
        numberOfServers.setColumns(10);
        numberOfServers.setBounds(727, 158, 146, 36);
        frame.getContentPane().add(numberOfServers);

        numberOfServersLabel = new JLabel("Number of queues =");
        numberOfServersLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        numberOfServersLabel.setBounds(522, 165, 191, 29);
        frame.getContentPane().add(numberOfServersLabel);

        start = new JButton("Start");
        start.setFont(new Font("Tahoma", Font.PLAIN, 20));
        start.setBounds(331, 228, 146, 36);
        frame.getContentPane().add(start);

        reset = new JButton("Reset");
        reset.setFont(new Font("Tahoma", Font.PLAIN, 20));
        reset.setBounds(521, 228, 146, 36);
        frame.getContentPane().add(reset);

        strategyChoice = new JComboBox();
        strategyChoice.setModel(new DefaultComboBoxModel(new String[] {"SHORTEST_TIME", "SHORTEST_QUEUE"}));
        strategyChoice.setSelectedIndex(1);
        strategyChoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        strategyChoice.setBounds(716, 14, 209, 36);
        frame.getContentPane().add(strategyChoice);

        startegyChoiceLabel = new JLabel("Choose the strategy:");
        startegyChoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        startegyChoiceLabel.setBounds(515, 14, 191, 38);
        frame.getContentPane().add(startegyChoiceLabel);

        textArea = new JTextArea();
        textArea.setBounds(10, 286, 1004, 314);
        textArea.setEditable(false);

        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(10, 296, 1004, 318);
        frame.getContentPane().add(scrollBar);

        frame.setVisible(true);
    }


    String getUserTimeLimit(){return timeLimit.getText();}
    String getUserMaxProcessingTime(){return maxProcessingTime.getText();}
    String getUserMinProcessingTime(){return minProcessingTime.getText();}
    String getUserMaxArrivalTime(){return maxArrivalTime.getText();}
    String getUserMinArrivalTime(){return minArrivalTime.getText();}
    String getUserNumberOfClients(){return numberOfClients.getText();}
    String getUserNumberOfQueues(){return numberOfServers.getText();}

    public void addStartListener(ActionListener mal){start.addActionListener(mal);}
    public void addResetListener(ActionListener mal){reset.addActionListener(mal);}

    public JTextField getTimeLimit() {
        return timeLimit;
    }

    public JTextField getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public JTextField getMinProcessingTime() {
        return minProcessingTime;
    }

    public JTextField getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public JTextField getMinArrivalTime() {
        return minArrivalTime;
    }

    public JTextField getNumberOfServers() {
        return numberOfServers;
    }

    public JTextField getNumberOfClients() {
        return numberOfClients;
    }

    public JComboBox getStrategyChoice() {
        return strategyChoice;
    }

    public static JTextArea getTextArea() {
        return textArea;
    }

}
