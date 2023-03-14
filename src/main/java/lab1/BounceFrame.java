package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private static BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public static BallCanvas getCanvas() {
        return canvas;
    }

    private BallThread createThread(Color color){
        Ball b = new Ball(canvas, color);
        canvas.add(b);
        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Thread name = " +
                thread.getName());
        return thread;
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              BallThread r_thread = createThread(Color.RED);
                                              try {
                                                  r_thread.join(2000);
                                              } catch (InterruptedException er) {
                                                  er.printStackTrace();
                                              }
                                              for (int i = 0; i < 10; i++) {
                                                  createThread(Color.BLUE);
                                              }
                                          }
                                      });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
