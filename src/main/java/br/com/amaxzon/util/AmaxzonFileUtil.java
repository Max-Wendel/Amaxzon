package br.com.amaxzon.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class AmaxzonFileUtil {

    public static void salvarImagem(String path, MultipartFile imagem) {
        File file = new File(path);
        try {
            FileUtils.writeByteArrayToFile(file,imagem.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
