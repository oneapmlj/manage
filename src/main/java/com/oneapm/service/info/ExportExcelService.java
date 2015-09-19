package com.oneapm.service.info;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

import com.oneapm.dto.Download;
import com.oneapm.dto.info.ExcelDto;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.mail.SendCloudDto;
import com.oneapm.service.mail.CloudService;
import com.oneapm.util.OneTools;

public class ExportExcelService<T>  
{  
		public void exportExcel(Collection<T> dataset, OutputStream out) {
			exportExcel("测试POI导出EXCEL文档", null, dataset,  "yyyy-MM-dd");
		}
	
		public HSSFWorkbook exportExcel(String[] headers, Collection<T> dataset) {
			return exportExcel("测试POI导出EXCEL文档", headers, dataset,  "yyyy-MM-dd");
		}
	
		public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
			exportExcel("测试POI导出EXCEL文档", headers, dataset,  pattern);
		}
	
		@SuppressWarnings("unchecked")
	
		public HSSFWorkbook exportExcel(String title, String[] headers, Collection<T> dataset,  String pattern) {
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 15);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置这些样式
			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 生成一个字体
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// 把字体应用到当前的样式
			style.setFont(font);
			// 生成并设置另一个样式
			HSSFCellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			// 把字体应用到当前的样式
			style2.setFont(font2);
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			// 遍历集合数据，产生数据行
			Iterator<T> it = dataset.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T dto = (T) it.next();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = dto.getClass().getDeclaredFields();
				for (short i = 0; i < fields.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style2);
					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						Class tCls = dto.getClass();
						Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
						Object value = getMethod.invoke(dto, new Object[] {});
						// 判断值的类型后进行强制类型转换
						String textValue = null;
						if (value instanceof Boolean) {
							boolean bValue = (Boolean) value;
							textValue = "是";
							if (!bValue) {
								textValue = "否";
							}
						}
						else if (value instanceof Date) {
							Date date = (Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							textValue = sdf.format(date);
						} else {
							textValue = value.toString();
						}
						if (textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if (matcher.matches()) {
								cell.setCellValue(Double.parseDouble(textValue));
							} else {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								HSSFFont font3 = workbook.createFont();
								font3.setColor(HSSFColor.BLUE.index);
								richString.applyFont(font3);
								cell.setCellValue(richString);
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} finally {
					}
				}
			}
			/*try {
				//workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			return workbook;
		}
		public static HSSFWorkbook exportExcelJson(List<Info> infoList) throws IOException{
			HSSFWorkbook workbook = null;
			 	List<ExcelDto> excelList = new ArrayList<ExcelDto>();
			 	for(Info info:infoList){
			 	ExcelDto dto = new ExcelDto();
	            dto.setUserId(info.getUserId().toString());
	            dto.setName(info.getName());
	            dto.setEmail(info.getEmail());
	            dto.setPhone(info.getPhone());
	            if(info.getQq()==null||info.getQq().trim().equals("null")){
	            	dto.setQq("无");
	            }else{
	            	dto.setQq(info.getQq());
	            }
	            if(info.getDownloads()==null||info.getDownloads().isEmpty()){
	            	dto.setDowload(false);
	            }else{
	            	dto.setDowload(true);
	            }
	            if(info.getDownloads()==null||info.getDownloads().isEmpty()){
	            	dto.setAgent("无");
	            }else{
	            StringBuffer sub = new StringBuffer();
	            for(Download download:info.getDownloads()){
	            	sub.append(download.getAgentName()+",");
	            }
	            String str = sub.toString().substring(0, sub.toString().length()-1);
	            dto.setAgent(str);
	            } 
	            if(info.getPreSaleName()==null||info.getPreSaleName().trim()=="null"){
	            	dto.setSupporter("无");
	            }else{
	            dto.setSupporter(info.getPreSaleName());
	            }
	            List<SendCloudDto> list = CloudService.findSendCloudBeanByEmail(info.getEmail());
	            int openNum = 0;
	            int clickNum = 0;
	            for(SendCloudDto scdto : list){
	            	if(scdto.getEvent().trim().equals("open")){
	            		openNum++;
	            	}
	            	if(scdto.getEvent().trim().equals("click")){
	            		clickNum++;
	            	}
	            }
	            dto.setOpenNum(Integer.toString(openNum));
	            dto.setClickNum(Integer.toString(clickNum));
	            excelList.add(dto);
			 	}
	            String result=null;
	            if (excelList != null) {
	            	String[] headers =   { "用户ID", "姓名", "邮箱", "电话", "QQ", "下载与否", "安装探针", "跟进人员", "15天内打开次数", "15天内点击次数" };  
	            	List<ExcelDto> dataset = new ArrayList<ExcelDto>(); 
	            	dataset.addAll(excelList);
	            	ExportExcelService<ExcelDto> ex = new ExportExcelService<ExcelDto>();  
	            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	            	workbook = ex.exportExcel(headers, dataset);  
					
		            result = OneTools.getResult(1, "导出成功，路径为d:\\用户邮件分析Excel\\"+df.format(new Date())+".xls");
	            }else{
	            	result = OneTools.getResult(0, "没有此用户");
	            }
				return workbook;
			
			
			
		}
	    
}

	 
