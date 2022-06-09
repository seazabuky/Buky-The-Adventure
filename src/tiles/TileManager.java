package tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import graphic.Sprite;
import tiles.blocks.NormBlock;
import util.Camera;

public class TileManager {

    public static ArrayList<TileMap> tm;
    public Camera cam;

    private int blockWidth;
    private int blockHeight;
    protected int width; 
    protected int height;

    public TileManager() {
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path, Camera cam) {
        this();
        addTileMap(path, 64, 64, cam);
    }

    public TileManager(String path, int blockWidth, int blockHeight, Camera cam) {
        this();
        addTileMap(path, blockWidth, blockHeight, cam);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight, Camera camera) {
        cam = camera;
        cam.setTileSize(blockWidth);
        String imagePath;

        tm = new ArrayList<TileMap>();

        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileColumns;
        int layers = 0;
        Sprite sprite;

        
        String[] data = new String[10];

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePath = eElement.getAttribute("name");
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
            sprite = new Sprite("map/tileset/" + imagePath + ".png", tileWidth, tileHeight);
            list = doc.getElementsByTagName("layer");
            layers = list.getLength();
            System.out.println("Columns " + tileColumns);
            for (int i = 0; i < layers; i++) {
                System.out.println("Layer: " + i + 1);
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                // System.out.println("Data: " + data[i]);
                if (i >= 1) {
                    tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                    System.out.println("MapNor success");
                } else {
                    System.out.println("MapNor success");
                    tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                    System.out.println("MapObj success");
                }
            }
            cam.setLimit(width * blockWidth, height * blockHeight);
        } catch (Exception e) {
            System.out.println("ERROR - TILEMANAGER: can not read tilemap:");
            e.printStackTrace();
            System.exit(0);
        }
        this.width = width;
        this.height = height;
    }

    public NormBlock[] getNormalTile(int id) {
        int normMap = 1;
        if(tm.size() < 2) normMap = 0; 
        NormBlock[] block = new NormBlock[9];

        int i = 0;
        for(int x = 1; x > -2; x--) {
            for(int y = 1; y > -2; y--) {
                if(id + (y + x * height) < 0 || id + (y + x * height) > (width * height) - 2) continue;
                block[i] = (NormBlock) tm.get(normMap).getBlocks()[id + (y + x * height)];
                i++;
            }
        }

        return block;
    }

    public int getBlockWidth() { return blockWidth; }
    public int getBlockHeight() { return blockHeight; }

    public void render(Graphics2D g) {
        if (cam == null) {
            return;
        }
        for (int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g, cam.getBounds());
        }
    }
}