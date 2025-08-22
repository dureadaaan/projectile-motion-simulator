// package gui;

// import javax.swing.*;
// import java.awt.CardLayout;
// import java.awt.*;
// // import java.render.animation;
// import render.AnimationPanel;

// import physics.SimulationResult;
// import render.AnimationPanel;

// public class MainFrame extends JFrame {

//     // Layout Manager
//     private CardLayout cardLayout;

//     // Panels 
//     private MainMenuPanel menuPanel;
//     private InputPanel inputPanel;
//     private ResultPanel resultPanel;
//     private AnimationPanel animationPanel; 

//     // Constructor
//     public MainFrame() {
    	
//         setTitle("Projectile Motion Simulator");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         // Initialize layout manager
//         cardLayout = new CardLayout();
//         getContentPane().setLayout(cardLayout);

//         // Initialize panels 
//         menuPanel = new MainMenuPanel();
//         menuPanel.setupMenuActions(this);
//         inputPanel = new InputPanel();
//         resultPanel = new ResultPanel(this);
//         animationPanel = new AnimationPanel();// Placeholder until AnimationPanel is created
//         getContentPane().add(animationPanel, "animation");

//         // Add panels to card layout
//         getContentPane().add(menuPanel, "menu");
//         getContentPane().add(inputPanel, "input");
//         getContentPane().add(resultPanel, "result");
//         getContentPane().add(animationPanel, "animation");
 
//         // Start with the main menu
//         showMenuPanel();

//         setVisible(true);
        
//     }


// 	// Show  menu panel
//     public void showMenuPanel() {
//         cardLayout.show(getContentPane(), "menu");
//     }
    

//     // Show the input form (custom or default)
//     public void showInputForm(boolean isCustom) {
//         inputPanel.setupForm(isCustom); // You'll define this method later
//         inputPanel.attachSimulateAction(this, isCustom);
//         inputPanel.attachBackAction(this);
//         cardLayout.show(getContentPane(), "input");
//     }

//     // // Show the result panel with simulation output
//     // public void showResult(SimulationResult result) {
//     //     resultPanel.displayResult(result); // You'll define this method later
//     //     cardLayout.show(getContentPane(), "result");
//     // }

//     // // Start the animation using the animation panel
//     // public void startAnimation(SimulationResult result, InputData input) {
//     //     // Example: animationPanel.startAnimation(result.getTrajectoryPoints());
//     //     cardLayout.show(getContentPane(), "animation");
//     //         animationPanel.startAnimation(result,input);
//     // cardLayout.show(getContentPane(), "animation");
//     // }

//     public void showResult(SimulationResult result, InputData input) {
//     resultPanel.displayResult(result, input); // ✅ Now passing both values
//     cardLayout.show(getContentPane(), "result");
// }


//     /** ✅ FINAL: animation uses both result and inputData */
//     public void startAnimation(SimulationResult result, InputData input) {
//         animationPanel.startAnimation(result, input);
//         cardLayout.show(getContentPane(), "animation");
//     }
// }




package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import physics.SimulationResult;
import render.AnimationPanel;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;

    private MainMenuPanel menuPanel;
    private InputPanel inputPanel;
    private ResultPanel resultPanel;
    private AnimationPanel animationPanel;

    public MainFrame() {
        setTitle("Projectile Motion Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);

        menuPanel = new MainMenuPanel();
        menuPanel.setupMenuActions(this);

        inputPanel = new InputPanel();
        resultPanel = new ResultPanel(this);
        animationPanel = new AnimationPanel();

        getContentPane().add(menuPanel, "menu");
        getContentPane().add(inputPanel, "input");
        getContentPane().add(resultPanel, "result");
        getContentPane().add(animationPanel, "animation");

        showMenuPanel();

        setVisible(true);
    }

    public void showMenuPanel() {
        cardLayout.show(getContentPane(), "menu");
    }

    public void showInputForm(boolean isCustom) {
        inputPanel.setupForm(isCustom);
        inputPanel.attachSimulateAction(this, isCustom);
        inputPanel.attachBackAction(this);
        cardLayout.show(getContentPane(), "input");
    }

    /** ✅ UPDATED: Accepts both result and input for full context */
    public void showResult(SimulationResult result, InputData inputData) {
        resultPanel.displayResult(result, inputData);
        cardLayout.show(getContentPane(), "result");
    }

    /** ✅ FINAL: animation uses both result and inputData */
    public void startAnimation(SimulationResult result, InputData input) {
        animationPanel.startAnimation(result, input);
        cardLayout.show(getContentPane(), "animation");
    }
}
