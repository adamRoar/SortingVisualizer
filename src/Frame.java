import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JFrame {

    public static int pixelsPerSquare = 4;
    final static int TITLE_BAR_HEIGHT = 22;

    public Frame() {

        initUI();
    }

    private void initUI() {

        final Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Sorting Visualization");
        setSize(255*pixelsPerSquare,255*pixelsPerSquare+TITLE_BAR_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame ex = new Frame();
                ex.setVisible(true);
            }
        });

    }
}

class Surface extends JPanel implements ActionListener, KeyListener {

    public SortingVisualizer sv = new QuickSortVisualizer(this);
    public int delay = 50;
    private Timer timer;
    public boolean paused = false;
    private Thread thread;

    public Surface() {

        initTimer();
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {    //onClick event
                repaint();
            }
        });
        addKeyListener(this);
        setFocusable(true);
    }

    private void initTimer() {

        timer = new Timer(delay, this);
        timer.start();
    }

    public Timer getTimer() {

        return timer;
    }

    public void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        int[] randomList = sv.getRandomList();
        for(int i = 0; i < randomList.length; i++) {
            g2d.setPaint(new Color(randomList[i],randomList[i],randomList[i]));
            g2d.fillRect(i * Frame.pixelsPerSquare,0, Frame.pixelsPerSquare, randomList.length*Frame.pixelsPerSquare);
        }



        g2d.setPaint(Color.red);
        g2d.drawString("Swaps: " + sv.getSwaps(), 3, 25);
        g2d.drawString("Array Accesses: " + sv.getArrayAccesses(),3,38);

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {    //timer update
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Random rd = new Random();
        switch(e.getKeyChar()) {
            case 'r':
                sv.randomizeList();
                break;
            case 'f':
                sv.reverseList();
                break;
            case 's':
                // double-checked locking pattern - not necessary in this case
                if(thread == null) {
                    synchronized (this) {
                        if(thread == null) {
                            thread = new Thread(() -> sv.sort());
                            thread.start();
                        }
                    }
                }

                break;
            case 'b':
                sv = new BubbleSortVisualizer(this);
                break;
            case 'q':
                sv = new QuickSortVisualizer(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void sortComplete() {
        thread = null;
        System.out.println("finished");
    }
}