package org.springframework.samples.mvc.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.bplow.todo.sample.sevice.ReadExcelToDbService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@Autowired
	ReadExcelToDbService readExcelToDbService;
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method=RequestMethod.GET)
	public void fileUploadForm() {
	}

	@RequestMapping(method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
		//得到上传文件的输入流
		InputStream is =  file.getInputStream();
		
		readExcelToDbService.readExcel(is);
		//
		/*OutputStream out = new FileOutputStream(new File("D:/"+ file.getOriginalFilename()));
		
		IOUtils.copy(is, out);
		out.flush();
		out.close();*/
		
	}
	
}
