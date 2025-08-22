// package gui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import physics.SimulationResult;

// public class ResultPanel extends JPanel {

//     private JLabel lblRangeValue;
//     private JLabel lblTimeOfFlightValue;
//     private JLabel lblPeakTimeValue;
//     private JButton btnBack;
//     private MainFrame parent;

//     public ResultPanel() {}

//     public ResultPanel(MainFrame parent) {
//         this.parent = parent;

//         setLayout(new BorderLayout(20, 20));
//         setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
//         setOpaque(false);  // We'll draw the background gradient manually

//         // Title label
//         JLabel title = new JLabel("Simulation Result", SwingConstants.CENTER);
//         title.setFont(new Font("Segoe UI", Font.BOLD, 28));
// //        title.setForeground(new Color(30, 144, 255)); // Dodger Blue
//         add(title, BorderLayout.NORTH);

//         // Center panel for result fields
//         JPanel resultPanel = new JPanel();
//         resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
//         resultPanel.setOpaque(false);

//         resultPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
//         resultPanel.add(createResultField("Range:", ""));
//         resultPanel.add(Box.createVerticalStrut(25));
//         resultPanel.add(createResultField("Time of Flight:", ""));
//         resultPanel.add(Box.createVerticalStrut(25));
//         resultPanel.add(createResultField("Peak Time:", ""));

//         add(resultPanel, BorderLayout.CENTER);

//         // Back button panel
//         JPanel btnPanel = new JPanel();
//         btnPanel.setOpaque(false);

//         btnBack = new JButton("Back to Menu");
//         btnBack.setPreferredSize(new Dimension(180, 50));
//         btnBack.setBackground(new Color(220, 53, 69));  // Bootstrap Danger Red
//         btnBack.setForeground(Color.WHITE);
//         btnBack.setFocusPainted(false);
//         btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
//         btnBack.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 parent.showMenuPanel();
//             }
//         });

//         btnPanel.add(btnBack);
//         add(btnPanel, BorderLayout.SOUTH);
//     }

//     // Creates a panel with label above and value below
//     private JPanel createResultField(String labelText, String valueText) {
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//         panel.setOpaque(false);

//         JLabel label = new JLabel(labelText, SwingConstants.CENTER);
//         label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//         label.setForeground(Color.DARK_GRAY);
//         label.setAlignmentX(Component.CENTER_ALIGNMENT);

//         JLabel value = new JLabel(valueText, SwingConstants.CENTER);
//         value.setFont(new Font("Segoe UI", Font.BOLD, 22));
//         value.setForeground(new Color(30, 144, 255)); // Dodger Blue
//         value.setAlignmentX(Component.CENTER_ALIGNMENT);

//         panel.add(label);
//         panel.add(Box.createVerticalStrut(8));
//         panel.add(value);

//         // Save reference for updates
//         switch (labelText) {
//             case "Range:" -> lblRangeValue = value;
//             case "Time of Flight:" -> lblTimeOfFlightValue = value;
//             case "Peak Time:" -> lblPeakTimeValue = value;
//         }

//         return panel;
//     }

//     // Gradient background
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         Graphics2D g2d = (Graphics2D) g;
//         int w = getWidth();
//         int h = getHeight();

//         GradientPaint gp = new GradientPaint(
//                 0, 0, new Color(245, 245, 255),  // Light top
//                 0, h, new Color(220, 240, 255)   // Slightly darker bottom
//         );
//         g2d.setPaint(gp);
//         g2d.fillRect(0, 0, w, h);
//     }

//     // Updates displayed values
//     public void displayResult(SimulationResult result) {
//         lblRangeValue.setText(String.format("%.2f m", result.getRange()));
//         lblTimeOfFlightValue.setText(String.format("%.2f s", result.getTimeOfFlight()));
//         lblPeakTimeValue.setText(String.format("%.2f s", result.getPeakTime()));
//     }
// }






package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import physics.SimulationResult;

public class ResultPanel extends JPanel {

    private JLabel lblRangeValue;
    private JLabel lblTimeOfFlightValue;
    private JLabel lblPeakTimeValue;
    private JButton btnBack;
    private JButton btnShowAnimation;
    private MainFrame parent;
    private SimulationResult latestResult;
    private InputData inputData;


    public ResultPanel(MainFrame parent) {
        this.parent = parent;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        setOpaque(false); // we'll draw the background gradient manually

        // Title
        JLabel title = new JLabel("Simulation Result", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        // Center result fields
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setOpaque(false);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        resultPanel.add(createResultField("Range:", ""));
        resultPanel.add(Box.createVerticalStrut(25));
        resultPanel.add(createResultField("Time of Flight:", ""));
        resultPanel.add(Box.createVerticalStrut(25));
        resultPanel.add(createResultField("Peak Time:", ""));

        add(resultPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        btnBack = new JButton("Back to Menu");
        btnBack.setPreferredSize(new Dimension(180, 50));
        btnBack.setBackground(new Color(220, 53, 69)); // Bootstrap Red
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> parent.showMenuPanel());

        btnShowAnimation = new JButton("Show Animation");
        btnShowAnimation.setPreferredSize(new Dimension(180, 50));
        btnShowAnimation.setBackground(new Color(30, 144, 255)); // Dodger Blue
        btnShowAnimation.setForeground(Color.WHITE);
        btnShowAnimation.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnShowAnimation.setFocusPainted(false);
        btnShowAnimation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (latestResult != null) {
                   parent.startAnimation(latestResult, inputData);

                }
            }
        });

        btnPanel.add(btnBack);
        btnPanel.add(btnShowAnimation);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private JPanel createResultField(String labelText, String valueText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setForeground(Color.DARK_GRAY);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel value = new JLabel(valueText, SwingConstants.CENTER);
        value.setFont(new Font("Segoe UI", Font.BOLD, 22));
        value.setForeground(new Color(30, 144, 255));
        value.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(value);

        switch (labelText) {
            case "Range:" -> lblRangeValue = value;
            case "Time of Flight:" -> lblTimeOfFlightValue = value;
            case "Peak Time:" -> lblPeakTimeValue = value;
        }

        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // gradient background
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 255), 0, h, new Color(220, 240, 255));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    public void displayResult(SimulationResult result,InputData inputData) {
          this.inputData = inputData;
        latestResult = result;
        lblRangeValue.setText(String.format("%.2f m", result.getRange()));
        lblTimeOfFlightValue.setText(String.format("%.2f s", result.getTimeOfFlight()));
        lblPeakTimeValue.setText(String.format("%.2f s", result.getPeakTime()));
    }

    public void reset() {
        lblRangeValue.setText("");
        lblTimeOfFlightValue.setText("");
        lblPeakTimeValue.setText("");
        latestResult = null;
    }
}


