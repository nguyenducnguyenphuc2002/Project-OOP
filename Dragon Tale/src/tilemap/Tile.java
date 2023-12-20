package tilemap;

import java.awt.image.BufferedImage;

public class Tile {
<<<<<<< HEAD
	
	private BufferedImage image;
	private int type;
	public static final int TILESIZE = 30;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() { return image; }
	public int getType() { return type; }
	
=======
    private final BufferedImage image;
    private final int type;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    public static final int TILESIZE = 30;


    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getType() {
        return this.type;
    }
>>>>>>> NguyenPhuc
}
