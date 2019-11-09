
package dev.swt.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class MyFileIO {
    private MyFileIO() {
    }

    public static MyFile read(String path) {
        MyFile myFile = new MyFile();
        String line;
        Boolean endMeta = false;

        try {
            // Open file
            BufferedReader filereader = new BufferedReader(new FileReader(path));

            // Read file line by line
            while ((line = filereader.readLine()) != null) {
                if (endMeta) {
                    myFile.addContent(line + "\n");
                } else {
                    if (line.isEmpty()) {
                        endMeta = true;
                    } else {
                        String[] meta = line.split(Pattern.quote(":"));
                        myFile.setMeta(meta[0], meta[1]);
                    }
                }
            }

            // Close file
            filereader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } // end catch

        return myFile;
    }

    public static void write(String path, MyFile myFile) {

        try {
            // Datei oeffnen
            FileWriter writer = new FileWriter(path);

            Set<Entry<String, String>> entrySet = myFile.getMeta().entrySet();
            Iterator<Entry<String, String>> it = entrySet.iterator();

            // Write meta information to file
            while (it.hasNext()) {
                Map.Entry<String, String> meta = (Map.Entry<String, String>) it.next();
                writer.write(meta.getKey() + ":" + meta.getValue() + "\n");
            }

            // write content
            writer.write("\n");
            writer.write(myFile.getContent());

            // Datei schliessen
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } // end method write()
}