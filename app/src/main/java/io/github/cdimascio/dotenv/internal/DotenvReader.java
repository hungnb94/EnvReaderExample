package io.github.cdimascio.dotenv.internal;

import android.util.Log;

import io.github.cdimascio.dotenv.DotenvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Internal) Reads a .env file
 */
public class DotenvReader {
    private static final String TAG = "DotenvReader";
    /*
    private String directory;
    private String filename;
     */
    private InputStream inputStream;

    /**
     * Creates a dotenv reader
     * @param directory the directory containing the .env file
     * @param filename the file name of the .env file e.g. .env
    public DotenvReader(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
    }
    */

    public DotenvReader(InputStream stream) {
        this.inputStream = stream;
    }

    /**
     * (Internal) Reads the .env file
     * @return a list containing the contents of each line in the .env file
     * @throws DotenvException if a dotenv error occurs
     * @throws IOException if an I/O error occurs
    private List<String> readLines() throws DotenvException, IOException {
        String dir = directory
            .replaceAll("\\\\", "/")
            .replaceFirst("\\.env$", "")
            .replaceFirst("/$", "");

        String location = dir + "/" + filename;
        String lowerLocation = location.toLowerCase();
        Path path = lowerLocation.startsWith("file:") || lowerLocation.startsWith("android.resource:")
            ? Paths.get(URI.create(location))
            : Paths.get(location);

        if (Files.exists(path)) {
            return Files
                .lines(path)
                .collect(Collectors.toList());
        }

        try {
            return ClasspathHelper
                .loadFileFromClasspath(location.replaceFirst("^\\./", "/"))
                .collect(Collectors.toList());
        } catch (DotenvException e) {
            Path cwd = FileSystems.getDefault().getPath(".").toAbsolutePath().normalize();
            String cwdMessage = !path.isAbsolute() ? "(working directory: " + cwd + ")" : "";
            e.addSuppressed(new DotenvException("Could not find " + path + " on the file system " + cwdMessage));
            throw e;
        }
    }
    */

    public List<String> read() throws DotenvException {
        Log.e(TAG, "inputStream: " + inputStream);
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return ReaderUtils.readLines(reader);
    }
}
