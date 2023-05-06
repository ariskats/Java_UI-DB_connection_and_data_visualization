package frame.downloadForm;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/*
 *  This class is used to handle the downloading of the .json file.
 *  
 */

public class DownloadController {
    
    private ReplaceFile replaceFile = new ReplaceFile(this);
    private String jlink = "https://data.cityofnewyork.us/resource/5uac-w243.json";
    private boolean proceed,wasWindowClosed,method;
    private File file;
    
    public DownloadController(){
        checkFile();
        //Interconnector.parseDatabase();
    }
    
    /**
    * <p>
    *   Checking the integrity of the file.
    * </p>
    * <p2>
    *   This method sets the directory where the file will be saved.
    *   Also checks weather the file already exists.
    *   If it exists it will call the download method.
    *   Otherwise it will download it.
    * </p2>
    * @since 1.0
    */
    private void checkFile(){
        if(!wasWindowClosed){
            proceed = true;
            File file = new File(System.getProperty("user.dir")+"/src/downloads","nypd_data.json");
            this.file = file;
            if(file.exists()){
                if(method) download(true);
                else System.out.println("File loaded.");
            }
            else{
                System.out.println("File doesn't exist.\nNow downloading.");
                download(false);
            }
        }else{
            proceed = false;
        }
    }
    
    /**
    * <p>
    *   The method where the downloading takes place.
    * </p>
    * <p2>
    *   This method downloads the .json file from a predetermined URL.
    *   After it is downloaded it is saved in a local folder.
    * </p2>
    * @since 1.0
    */
    private void download(boolean replacing){
        try {
            
		String source = jlink;
		URL url = new URL(source);
		URLConnection conexion = url.openConnection();
		conexion.connect();
                
		//int lenghtOfFile = conexion.getContentLength();    
                InputStream input = new BufferedInputStream(url.openStream());
                
		// Copy file
                if(replacing){
                    File deathrow = new File(System.getProperty("user.dir")+"/src/downloads","nypd_data.json");
                    if(deathrow.delete()) System.err.println("Replacing...");
                    else System.err.println("Cannot delete what does not exist.");
                }
                String saveFile = null;
		try {
                    saveFile = new File(".").getCanonicalPath() + "\\src\\downloads\\" + "nypd_data.json";
		} catch (IOException e1) {
                    e1.printStackTrace();
		}
                OutputStream output = new FileOutputStream(saveFile);
					
		byte data[] = new byte[1024];
		int count;			
		while ((count = input.read(data)) != -1) output.write(data, 0, count);
                
		output.flush();
		output.close();
		input.close();
                System.err.println("Download complete.");
				        
	} catch (Exception ex) {
                System.err.println(ex);
	}
    }
    
    public boolean isProceed() {
        return proceed;
    }

    public File getFile() {
        return file;
    }

    public void setWasWindowClosed(boolean wasWindowClosed) {
        this.wasWindowClosed = wasWindowClosed;
    }

    void setMethod(boolean method) {
        this.method = method;
    }
    
}
