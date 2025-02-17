package com.GGB.upload.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {

	@GetMapping("/upload")
	public String upload() {
		return "upload/upload";
	}

	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, Map<String, Object> paramHeader)
			throws JsonMappingException, JsonProcessingException {// (@RequestParam("file")
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");

		// JSON 데이터 가져오기
		String jsonString = request.getParameter("paramHeader");
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("Received paramMap: " + jsonString);
		List<Map<String, Object>> headerData = objectMapper.readValue(jsonString,
				new TypeReference<List<Map<String, Object>>>() {
				});

		log.info("jsonData: " + headerData);
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		Map<String, Object> resultMap = new HashMap<>();
		try (InputStream is = file.getInputStream()) {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(1);

			List<String> header = new ArrayList<>();
			List<Map<String, Object>> data = new ArrayList<>();
			DataFormatter dataFormatter = new DataFormatter();

			Iterator<Row> rowIterator = sheet.iterator();
			// 헤더 처리
			if (rowIterator.hasNext()) {
				Row headerRow = rowIterator.next();
				for (Cell cell : headerRow) {
					header.add(dataFormatter.formatCellValue(cell));
				}
			}
			log.error("rowHeader : " + header);
			String paramStr = "";
			String nameStr = "";
			String str = "";
			for(Map<String, Object> map : headerData) {
				paramStr = (String) map.get("header");
				nameStr = (String) map.get("name");
				for(int i = 0 ; i < header.size() ; i++) {
					str = header.get(i);
					if(str.equals(paramStr)) {
						header.set(i, nameStr);
					}
				}
			}
			log.error("rowHeader : " + header);
			// 데이터 처리
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Map<String, Object> rowData = new HashedMap<>();
				for (int i = 0; i < header.size(); i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData.put(header.get(i), dataFormatter.formatCellValue(cell));
				}
				data.add(rowData);
			}

			resultMap.put("data", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// log.error(((List<Map<String, Object>>)resultMap).get(0).toString());
		return resultMap;
	}

}
