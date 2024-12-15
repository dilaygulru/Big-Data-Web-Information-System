package org.example.hadoopproject.Services;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
public class HdfsService {
    private final String HDFS_URL = "hdfs://localhost:9000"; // HDFS adresi

    // Dosyayı yerel sistemden HDFS'ye yükler
    public void uploadFile(String localFilePath, String hdfsFilePath) throws IOException {
        // HDFS bağlantısı için yapılandırmayı ayarlayın
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_URL); // HDFS URL'sini ayarla

        // HDFS FileSystem nesnesini al
        FileSystem fileSystem = FileSystem.get(URI.create(HDFS_URL), configuration);

        try {
            Path localPath = new Path(localFilePath);
            Path hdfsPath = new Path(hdfsFilePath);
            // Yerel dosyayı HDFS'ye kopyala
            fileSystem.copyFromLocalFile(localPath, hdfsPath);
        } catch (IOException e) {
            throw new IOException("HDFS'ye dosya yüklenirken hata oluştu", e);
        } finally {
            fileSystem.close(); // FileSystem nesnesini kapatmayı unutmayın
        }
    }

    // HDFS'deki dosya URL'sini al
    public String getFileUrl(String fileName) {
        return "http://localhost:9870/webhdfs/v1/images/" + fileName + "?op=OPEN"; // WebHDFS URL'si
    }

}
