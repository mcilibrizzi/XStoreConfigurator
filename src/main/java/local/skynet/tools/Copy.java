package local.skynet.tools;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Copy {
    public static void copy(String sourceFolder , String destFolder,String pattern){
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);

        try {
            Files.walkFileTree(Paths.get(sourceFolder),new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(matcher.matches(file) ){
                        Files.copy(file, Paths.get(destFolder+file.getFileName()),StandardCopyOption.REPLACE_EXISTING);
                    }
                    return FileVisitResult.CONTINUE;

                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void copy(String sourceFolder , String destFolder){
        try {
            Files.walkFileTree(Paths.get(sourceFolder), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file,Paths.get(destFolder+file.getFileName()),StandardCopyOption.REPLACE_EXISTING);
                    return  FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
