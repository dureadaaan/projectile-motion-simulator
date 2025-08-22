package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import physics.PhysicsEngine;
import physics.SimulationResult;

public class InputPanel extends JPanel {

    private JTextField angleField;
    private JTextField velocityField;
    private JTextField heightField;
    private JTextField gravityField;
    private JTextField resistanceField;
    private JButton btnSimulate;
    private JButton btnBack;

    public InputPanel() {
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Enter Simulation Parameters", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Central Form Panel with gradient background
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(245, 245, 255),
                        0, getHeight(), new Color(220, 240, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Fields
        angleField = createInputField("Angle (degrees):", formPanel);
        velocityField = createInputField("Velocity (m/sec):", formPanel);
        heightField = createInputField("Height (meters):", formPanel);
        gravityField = createInputField("Gravity (m/sec²):", formPanel);
        resistanceField = createInputField("Air Resistance:", formPanel);

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));

        btnSimulate = createStyledButton("Simulate", new Color(76, 175, 80)); // Green
        btnBack = createStyledButton("Back", new Color(244, 67, 54)); // Red

        buttonPanel.add(btnSimulate);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createInputField(String labelText, JPanel parent) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(250, 40));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        parent.add(label);
        parent.add(Box.createVerticalStrut(5));
        parent.add(field);
        parent.add(Box.createVerticalStrut(15));

        return field;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 45));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public InputData collectInput(boolean isCustom) {
        try {
            String angleValue = angleField.getText();
            String velocityValue = velocityField.getText();
            String heightValue = heightField.getText();
            String gravityValue = gravityField.getText();
            String resistanceValue = resistanceField.getText();

            if (angleValue.isEmpty() || velocityValue.isEmpty() || heightValue.isEmpty()
                    || gravityValue.isEmpty() || resistanceValue.isEmpty()) {
                throw new IllegalArgumentException("Please fill in all the fields.");
            }

            double angle = Double.parseDouble(angleValue);
            double velocity = Double.parseDouble(velocityValue);
            double height = Double.parseDouble(heightValue);
            double gravity = Double.parseDouble(gravityValue);
            double resistance = Double.parseDouble(resistanceValue);

            return new InputData(angle, velocity, height, gravity, resistance, isCustom);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void setupForm(boolean isCustom) {
        if (isCustom) {
            angleField.setText("");
            velocityField.setText("");
            heightField.setText("");
            gravityField.setText("");
            gravityField.setEditable(true);
            resistanceField.setText("");
            resistanceField.setEditable(true);
        } else {
            angleField.setText("");
            velocityField.setText("");
            heightField.setText("");
            gravityField.setText("9.8");
            gravityField.setEditable(false);
            resistanceField.setText("0.0");
            resistanceField.setEditable(false);
        }
    }

    public void attachSimulateAction(MainFrame parent, boolean isCustom) {
        btnSimulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InputData input = collectInput(isCustom);  // Collects user input
                if (input != null) {
                    
                    SimulationResult result = PhysicsEngine.simulate(input);
                    parent.showResult(result,input); // Pass real result to be displayed
                }
            }
        });
    }


    public void attachBackAction(final MainFrame parent) {
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showMenuPanel();
            }
        });
    }
}
