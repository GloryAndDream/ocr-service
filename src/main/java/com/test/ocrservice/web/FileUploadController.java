package com.test.ocrservice.web;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.ocrservice.service.TesseractOCRService;

@Controller
public class FileUploadController {

	@Value("${file.upload.path}")
	private String filePath;

	@Autowired
	TesseractOCRService tesseractOCRService;

	@RequestMapping("/")
	public String index() {
		return "upload";
	}

	@RequestMapping(value = "/processOCR", method = RequestMethod.POST)
	public @ResponseBody String[] singleFileUpload(@RequestParam("file") MultipartFile file, Model model)
			throws Exception {

		String fileName = file.getOriginalFilename();

		String path = filePath;

		File filepath = new File(path, fileName);

		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		try {
			file.transferTo(new File(path + File.separator + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String text = tesseractOCRService.processImg(filepath);

		String[] array = { "images/" + fileName, text };
		// model.addAttribute("filename", "/images/" + fileName);
		// model.addAttribute("text", text);

		return array;
	}

}