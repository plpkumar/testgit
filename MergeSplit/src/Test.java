import java.io.*;
import java.util.Vector;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.JAI;
import java.awt.image.renderable.ParameterBlock;
 

public class Test {
 
    public static void main(String[] args) throws IOException {
        String filename = "C:\\Users\\saipavan.kumar\\Desktop\\{35EC72D3-CC0C-448C-8CD2-005B87544D99}\\combined.tiff";
    	new Test().doitJAI();
        //new Test().mergeIt();
    	//new Test().processTIFF(filename);
    }
 
    public void  doitJAI() throws IOException {
    	
        FileSeekableStream ss = new FileSeekableStream("C:\\Users\\saipavan.kumar\\Desktop\\{35EC72D3-CC0C-448C-8CD2-005B87544D99}\\combined.tif");
        ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, null);
        int count = dec.getNumPages();
        int userCount = 2;
        TIFFEncodeParam param = new TIFFEncodeParam();
        param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
        param.setLittleEndian(false);
        System.out.println("This TIF has " + count + " image(s)");
        for (int i = 0; i < count; i++) {
        	
        	if(i < userCount){
	        	RenderedImage page = dec.decodeAsRenderedImage(i);
	            File f = new File("C:\\Users\\saipavan.kumar\\Desktop\\{35EC72D3-CC0C-448C-8CD2-005B87544D99}\\Single\\single_" + i + ".tif");
	            System.out.println("Saving " + f.getCanonicalPath());
	            ParameterBlock pb = new ParameterBlock();
	            pb.addSource(page);
	            pb.add(f.toString());
	            pb.add("tiff");
	            pb.add(param);
	            RenderedOp r = JAI.create("filestore",pb);
	            r.dispose();
        	}
        	else
        	{
        		RenderedImage page = dec.decodeAsRenderedImage(i);
	            File f = new File("C:\\Users\\saipavan.kumar\\Desktop\\{35EC72D3-CC0C-448C-8CD2-005B87544D99}\\Single\\single_" + i + ".tif");
	            System.out.println("Saving " + f.getCanonicalPath());
	            ParameterBlock pb = new ParameterBlock();
	            pb.addSource(page);
	            pb.add(f.toString());
	            pb.add("tiff");
	            pb.add(param);
	            RenderedOp r = JAI.create("filestore",pb);
	            r.dispose();
        	}
        }
    }
    
   
    @SuppressWarnings("deprecation")
	public void processTIFF(String filename) throws IOException {   
        PlanarImage image;   
        File file = new File(filename);   
        SeekableStream stream = new FileSeekableStream(file);   
        TIFFDecodeParam decodeParam = null;   
        ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", stream, decodeParam);   
        int numPages = decoder.getNumPages();   
        for (int i = 0; i < numPages; i++) {   
            image = new NullOpImage(decoder.decodeAsRenderedImage(i), null, OpImage.OP_IO_BOUND, null);   
            String newFileName = (i + 1) + ".tiff";   
            FileOutputStream fos = new FileOutputStream(newFileName);   
            TIFFEncodeParam encodeParam = null;   
            ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", fos, encodeParam);   
            encoder.encode(image);   
            fos.close();   
        }   
    }   
}