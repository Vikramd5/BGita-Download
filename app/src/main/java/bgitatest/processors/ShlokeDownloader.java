package bgitatest.processors;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import bgitatest.model.Shloke;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShlokeDownloader implements Runnable {

    Shloke shloke;
    public static Path basePath = Paths.get("output");

    @Override
    public void run() {
       
         System.out.printf("Dowloading %s:%s in %s\n", shloke.getChapterID(), shloke.getShlokeID(), shloke.getLang());
        try {
            String baseFileName=String.format("%04d %s-%s",shloke.getIndex(),shloke.getChapterID(),shloke.getShlokeID());
            InputStream audioStream = new URL(shloke.getAudioURL()).openStream();
            Path targetPath = Paths.get(basePath.toString(),String.format("%s",shloke.getLang()),baseFileName+".mp3");
            print(targetPath.toAbsolutePath()+" -> "+targetPath.toFile().mkdirs()+","+targetPath.toFile().createNewFile());
            
            Files.copy(audioStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            print("Audio donwloaded");
            targetPath = Paths.get(basePath.toString(),String.format("%s",shloke.getLang()),baseFileName+".srt");
            ByteArrayInputStream srtStream = new ByteArrayInputStream(shloke.getTraslatedShloke().getBytes("UTF-16"));
            Files.copy(srtStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            print("subtitles created");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.printf("Dowloaded %s:%s in %s\n", shloke.getChapterID(), shloke.getShlokeID(), shloke.getLang());

    }

    private void print(String msg)
    {
         System.out.printf("Shloke [%2s:%2s] %s\n", shloke.getChapterID(), shloke.getShlokeID(), msg);
    }

}
