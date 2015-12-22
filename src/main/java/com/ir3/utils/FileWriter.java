package com.ir3.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;

/**
 * @author MANJEET
 *
 */
public class FileWriter {
	
	public static final String FILE_PATH = "tweets\\";
	
	/*public void writeDataToFile(TweetsList list, String lang, String queryName, String timestamp) {
		String fileURI = FILE_PATH.concat(lang).concat("\\").concat(queryName).concat(timestamp).concat(".xml");
		try {
			File file = new File(fileURI);
			JAXBContext context = JAXBContext.newInstance(TweetsList.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(list, new FileOutputStream(file));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	
	public void writeDataToFile(JSONObject jsonData, String lang, String timestamp) {
		String fileURI = FILE_PATH.concat(lang).concat("\\").concat("tweets").concat(timestamp).concat(".json");
		try {
			OutputStream stream = new FileOutputStream(new File(fileURI));
			Writer streamWriter =  new OutputStreamWriter(stream, Charset.forName("UTF-8").newEncoder());
			streamWriter.write(jsonData.toJSONString());
			streamWriter.flush();
			streamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}