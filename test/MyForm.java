import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyForm extends JFrame {

	private JTextField txtURL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyForm form = new MyForm();
				form.setVisible(true);
			}
		});
	}

	public MyForm() {

		// Create Form Frame
		super("ThaiCreate.Com Java GUI Tutorial");
		setSize(525, 270);
		setLocation(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Label Title
		final JLabel lblTitle = new JLabel("Download File", JLabel.CENTER);
		lblTitle.setBounds(73, 24, 370, 14);
		getContentPane().add(lblTitle);

		// TextField URL
		txtURL = new JTextField("https://data.cityofnewyork.us/api/views/5uac-w243/files/08255f41-9bb1-40f7-bb8f-e8621d3a8916?download=true&filename=PDCode_PenalLaw.xlsx");
		txtURL.setHorizontalAlignment(SwingConstants.CENTER);
		txtURL.setBounds(73, 66, 370, 20);
		getContentPane().add(txtURL);

		// Button Download
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					URL url = new URL(txtURL.getText());
					
				    URLConnection conexion = url.openConnection();
				    conexion.connect();	    
				    int lenghtOfFile = conexion.getContentLength();    
				    InputStream input = new BufferedInputStream(url.openStream());
					
					// File Name
					String source = txtURL.getText();
					String fileName = source.substring(source.lastIndexOf('/') + 1, source.length());

					// Copy file
					String saveFile = null;
					try {
//						saveFile = new File(".").getCanonicalPath() + "\\downloads\\" + fileName;
//						saveFile = new File(".").getCanonicalPath() + "\\build\\classes\\downloads\\" + "PDCode_PenalLaw.xlsx";
						saveFile = new File(".").getCanonicalPath() + "\\src\\downloads\\" + "PDCode_PenalLaw.xlsx";
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                                        System.out.println(saveFile);
					OutputStream output = new FileOutputStream(saveFile);
					
				    byte data[] = new byte[1024];
				    int count;
				    
				    	long total = 0;
				
				        while ((count = input.read(data)) != -1) {
				        	total += count;
				            output.write(data, 0, count);
				        }
				
				        output.flush();
				        output.close();
				        input.close(); 
				        
				} catch (Exception ex) {
					System.err.println(ex);
				}

			}
		});
		btnDownload.setBounds(217, 118, 100, 23);
		getContentPane().add(btnDownload);

	}
}