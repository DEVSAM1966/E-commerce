package com.devsam.backend.ecommerce.application;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadFile {
    // Definición de la constante FOLDER para la ruta de las imagenes
    private final String FOLDER = "src//main//resources//static//images//";

    // Definición de la constante IMG_DEFAULT para tener la imagen por defecto
    private final String IMG_DEFAULT = "default.jpg";

    // Definición de la constante URL para tener el endpoint de carga de imagenes desde backend
    private final String URL = "http://localhost:8085/images/";

    // Guardar una imagen
    public String upload (MultipartFile multipartFile) throws IOException {
        // Detectar que la imagen recibida tenga algo (que no sea null)
        if (multipartFile != null) {

            // Creo una array de bytes para guardar la imagen
            byte [] bytes = multipartFile.getBytes();

            // Generamos la ruta con FOLDER + nombre del fichero imagen y se guarda en path
            Path path = Paths.get(FOLDER+multipartFile.getOriginalFilename());

            // Gravamos a disco el array de byte llamdo bytes por lo indicado en la variable path
            Files.write(path, bytes);

            // Devolvemos URL+nombre del fichero de la imagen
            return URL+multipartFile.getOriginalFilename();
        }

        // si no hay imagen valida devolvemos la imagen por defecto
        return URL+IMG_DEFAULT;
    }

    // Borrar una imagen
    public void delete(String nameFile) {
        // Guardo en file (de tipo File) el path+nombre de la imagen
        File file = new File((FOLDER+nameFile));

        // Se procede a borrar el archivo en disco
        file.delete();
    }
}
