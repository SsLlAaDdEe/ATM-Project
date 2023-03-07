import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
public class AtmDispenser extends JFrame{
    private JPanel panel1;
    private JButton withdrawAmountButton;
    private JButton depositAmountButton;
    private JButton checkBalanceButton;
    private JLabel balanceLbl;
    private JRadioButton a1000RadioButton;
    private JRadioButton a2000RadioButton;
    private JRadioButton a5000RadioButton;
    private JRadioButton a10000RadioButton;

    public AtmDispenser(String title) {
        super(title);
        setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        ButtonGroup btngrp = new ButtonGroup();
        btngrp.add(a1000RadioButton);
        btngrp.add(a2000RadioButton);
        btngrp.add(a5000RadioButton);
        btngrp.add(a10000RadioButton);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                String balance = "";
                try {
                    File file = new File("AccountInfo.txt");
                    Scanner scan = new Scanner(file);

                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        String[] data = line.split(" ");
                        if (data[0].equals("Victory")) {
                            balance = data[1];
                            found = true;
                            break;
                        }
                    }
                    scan.close();
                    if (found) {
                        balanceLbl.setText("Balance: "+ balance);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        withdrawAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                String balance = "";
                String radioremove = " ";
                if(a1000RadioButton.isSelected()){
                    radioremove = a1000RadioButton.getText();
                } else if (a2000RadioButton.isSelected()) {
                    radioremove = a2000RadioButton.getText();
                } else if (a5000RadioButton.isSelected()) {
                    radioremove = a5000RadioButton.getText();
                }
                else{
                    radioremove = a10000RadioButton.getText();
                }
                try {
                    File file = new File("AccountInfo.txt");
                    Scanner scan = new Scanner(file);

                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        String[] data = line.split(" ");
                        if (data[0].equals("Victory")) {
                            balance = data[1];
                            found = true;
                            break;
                        }
                    }
                    double currentbal = Double.parseDouble(balance);
                    double amount = Double.parseDouble(radioremove);
                    Double Newbal = currentbal - amount;
                    String data = "Victory" + " "+ Newbal.toString();
                    scan.close();

                    if (found) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(data);
                        writer.close();
                        // Display the new balance
                        JOptionPane.showMessageDialog(null, "Amount Has been Withdrawn", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        depositAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                String balance = "";
                String radioremove = " ";
                if(a1000RadioButton.isSelected()){
                    radioremove = a1000RadioButton.getText();
                } else if (a2000RadioButton.isSelected()) {
                    radioremove = a2000RadioButton.getText();
                } else if (a5000RadioButton.isSelected()) {
                    radioremove = a5000RadioButton.getText();
                }
                else{
                    radioremove = a10000RadioButton.getText();
                }
                try {
                    File file = new File("AccountInfo.txt");
                    Scanner scan = new Scanner(file);

                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        String[] data = line.split(" ");
                        if (data[0].equals("Victory")) {
                            balance = data[1];
                            found = true;
                            break;
                        }
                    }
                    double currentbal = Double.parseDouble(balance);
                    double amount = Double.parseDouble(radioremove);
                    Double Newbal = currentbal + amount;
                    String data = "Victory" + " "+ Newbal.toString();
                    scan.close();

                    if (found) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(data);
                        writer.close();
                        // Display the new balance
                        JOptionPane.showMessageDialog(null, "Amount Has been Deposited", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public static void main(String[] arg){
        JFrame frame = new AtmDispenser("Atm Machine");
        frame.setVisible(true);
    }
}
