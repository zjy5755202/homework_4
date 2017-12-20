package search.lmpl;

import search.FileHandler;
import vo.Program;

import java.io.*;
import java.util.List;

public class FileHandlerImpl implements FileHandler{
    public int program2File( List<Program> programList) throws IOException{
        File file = new File("programs.txt");
        int programNumber=0;
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,"UTF-8");
        String str;
        for(int i=0;i<programList.size();i++)
        {
            str=programList.get(i).toString();
            writer.write(str);
            programNumber++;
        }
        writer.close();
        outputStream.close();
        return  programNumber;
    }
}

