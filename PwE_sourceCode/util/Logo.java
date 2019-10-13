package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class for defining an object logo, characterized by a name, a path to the image-file
 * and the image of the logo
 */

public class Logo {

	private String name;
	private String path;
	private ImageView img;
	
	public Logo(String name, String path) {
		this.name = name;
		this.path = path;
		this.img = new ImageView(createImage(path));
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public ImageView getImg() {
		return this.img;
	}
	
	protected Image createImage(String path) {
		return new Image(Logo.class.getResourceAsStream(path));
	}
}