package drinks.service.storage;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import drinks.exception.FileException;

@Service
public class CloudinaryPhotoStorageService implements PhotoStorageService {

	private static final String FOLDER = "drinks/";

	@Override
	public String upload(byte[] file) {
		try {
			var cloudinary = new Cloudinary();
			var publicId = FOLDER.concat(UUID.randomUUID().toString());
			var map = cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", publicId));
			return (String) map.get("url");
		} catch (IOException e) {
			throw new FileException("Não foi possível salvar o arquivo");
		}
	}

	@Override
	public void delete(String photo) {
		if (Objects.isNull(photo) || photo.isBlank()) {
			return;
		}
		try {
			var cloudinary = new Cloudinary();
			cloudinary.uploader().destroy(publicId(photo), ObjectUtils.emptyMap());
		} catch (IOException e) {
			throw new FileException("Não foi possível deletar o arquivo");
		}
	}

	private String publicId(String photo) {
		return photo.substring(photo.lastIndexOf(FOLDER), photo.lastIndexOf('.'));
	}

}
