package com.lps.edc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.lps.edc.dto.TableDto;

 
public class CSV { 
 
	@SuppressWarnings("unchecked")
	public static void create(TableDto dto, HttpServletRequest req, ReportType type) { 
		StringBuffer sb = new StringBuffer();
		String dir = req.getServletContext().getRealPath("/") + "cvstemp";
		File filedir = new File(dir);
	    if (!filedir.exists())
	    	filedir.mkdir();
		String filename = req.getSession().getId() + "_" + type.toString() + ".csv";
		String pathname = dir + File.separator + filename;
		FileWriterWithEncoding fw = null;
		try {
			fw = new FileWriterWithEncoding(pathname, "gb2312");
			List<Object> title = dto.getTitle();
			int titleSize = title.size();
			for (int i = 0; i < titleSize; i++) {
				sb.append(title.get(i).toString());
				if (i == titleSize - 1) {
					sb.append("\n");
				} else {
					sb.append(",");
				}
			}
			List<Object> data = dto.getData();
			for (Object object : data) {
				List<Object> d = (List<Object>) object;
				int dataSize = d.size();
				for (int i = 0; i < dataSize; i++) {
					sb.append(d.get(i).toString());
					if (i == dataSize - 1) {
						sb.append("\n");
					} else {
						sb.append(",");
					}
				}
			}
			fw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void download(HttpServletRequest req, HttpServletResponse resp, ReportType type, String outFileName) {
		String pathname = req.getServletContext().getRealPath("/") + "cvstemp" + File.separator + req.getSession().getId() + "_" + type.toString() + ".csv";
		File file = new File(pathname);
		if (file.exists()) {
			FileInputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(file);
				resp.reset(); 
				resp.setContentType("text/csv;charset=GB2312"); 
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + outFileName + "\"");
				out = resp.getOutputStream();
				int temp;
				while ((temp = in.read()) != -1) {
					out.write(temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public enum ReportType {
		ANSWERNUMBER,CLASSAVG,QUESTIONSCORE,ORIGIALANSWER,CLASSPOWER,STUDENTPOWER,CLASSKNOWLEDGE,STUDENTKNOWLEDGE;
	}
}
