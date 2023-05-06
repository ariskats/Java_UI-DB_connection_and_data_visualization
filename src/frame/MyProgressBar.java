package frame;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

//NOT USED IN THE LATEST VERSION//

public class MyProgressBar extends JDialog{
    
    private int length;
    private static int progr;
    
    public MyProgressBar(int length){
        super();
        this.length = length;
        progr = 0;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
    }

    private void initComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        JProgressBar progress = new JProgressBar();
        progress.setStringPainted(true);
        contentPane.add(new JLabel("Loop progress is: "), BorderLayout.NORTH);
        contentPane.add(progress, BorderLayout.SOUTH);
        setContentPane(contentPane);
        ProgressWorker worker = new ProgressWorker(progress,this);
        worker.execute();
    }
    
    private static class ProgressWorker extends SwingWorker<Void, Integer> {
        private final JProgressBar progress;
        private MyProgressBar progressBar;

        public ProgressWorker(JProgressBar progress,MyProgressBar progressBar) {
            this.progress = progress;
            this.progressBar = progressBar;
        }

        @Override
        protected Void doInBackground() throws Exception {
//            for (long i = LOOP_LENGTH; i > 0; i--) {
//                final int progr = (int) ((100L * (LOOP_LENGTH - i)) / LOOP_LENGTH);
//                publish(progr);
//            }
            
            publish(progressBar.getProgr());
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            progress.setValue(chunks.get(chunks.size() - 1));
            super.process(chunks);
        }

        @Override
        protected void done() {
            progress.setValue(100);
        }
    }
    
    public void fillProgressBar(){
        progr += 1;
        if(progr == length){
            this.dispose();
        }
    }

    public int getProgr() {
        return progr;
    }
    
}
