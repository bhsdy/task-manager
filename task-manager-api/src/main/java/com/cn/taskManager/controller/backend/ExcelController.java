package com.cn.taskManager.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cn.taskManager.domain.entity.SysMenu;
import com.cn.taskManager.domain.service.backend.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "ExcelController", description="导入导出接口")
public class ExcelController {
    @Autowired
    private SysMenuService sysMenuService;
    public String getExcel(@RequestParam("file") MultipartFile file){

        return null;
    }
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录",produces="application/octet-stream")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("这个是表头");

        //创建表头
        setTitle(workbook, sheet);
        EntityWrapper<SysMenu> ew = new EntityWrapper<>();
        List<SysMenu> answers = sysMenuService.selectList(ew);

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (SysMenu answer:answers) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(answer.getPid());
//            row.createCell(1).setCellValue(answer.getFullName());
//            row.createCell(2).setCellValue(answer.getCheckes());
//            row.createCell(3).setCellValue(answer.getContent());
            rowNum++;
        }
        String fileName = "survey-answer.xlsx";
        //清空response
        response.reset();
        //设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="+ fileName);
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=GB2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 10*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 100*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("单选");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("多选");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("简答");
        cell.setCellStyle(style);
    }
}
