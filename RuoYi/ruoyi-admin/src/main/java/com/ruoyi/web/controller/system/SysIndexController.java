package com.ruoyi.web.controller.system;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.InfoRecord;
import com.ruoyi.system.service.IInfoRecordService;
import com.ruoyi.web.controller.tool.QRCodeTools;
import com.ruoyi.web.controller.tool.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页 业务处理
 * 
 * @author z
 */
@Controller
@PropertySource({"classpath:config.properties"})
public class SysIndexController extends BaseController
{

    @Value("${h5_files_path}")
    private String h5_files_path;


    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private IInfoRecordService iInfoRecordService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }

    @GetMapping("/h5/template")
    public String h5template(HttpServletRequest request)
    {
        String prefix="h5/test_wangzhuan";
        return prefix+request.getParameter("templateID");
    }

    @GetMapping("/h5/template_qq")
    public String template_qq(HttpServletRequest request)
    {
        String prefix="h5/test_wangzhuan_qq";
        return prefix+request.getParameter("templateID");
    }

    @GetMapping("/info/template")
    public String template(ModelMap mmap)
    {

        return "templateInfo";
    }

    @GetMapping("/info/template_qq")
    public String template_qq(ModelMap mmap)
    {

        return "templateInfo_qq";
    }

    @GetMapping("/info/uploadPhoto")
    public String uploadPhoto(ModelMap mmap)
    {

        return "uploadPhoto";
    }

    @RequestMapping("/info/savePhoto")
    public void savePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String wechatID="";
        String templateUrl=request.getParameter("templateUrl");
        String qrcodeImg="";

        JSONObject json=new JSONObject();

        if(org.apache.commons.lang3.StringUtils.isNotBlank(templateUrl)){
            templateUrl=templateUrl.substring(templateUrl.indexOf("ID=")+3);

            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if(multipartResolver.isMultipart(request)){

                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while(iter.hasNext()){
                    MultipartFile file = multiRequest.getFile(iter.next());

                    String originalName=file.getOriginalFilename();
                    String dst=originalName.substring(originalName.lastIndexOf("."));
                    System.out.println("updatePhoto-----fileName---------->" + file.getOriginalFilename());

                    System.err.println("dst:"+dst);
                    File file_resource = new File(h5_files_path+"/h5/wangzhuan"+templateUrl+"/images/qrcode.jpg");
                    if(file_resource.exists()){
                        file_resource.delete();
                    }
                    String filePath=Global.getAvatarPath();
                    File ff=new File(filePath);
                    if(!ff.exists()){
                        ff.mkdirs();
                    }
                    String photoName=getUUID()+dst;
                    File f=new File(filePath+"/"+photoName);
                    file.transferTo(file_resource);
                    /**
                     * 复制到指定盘符下
                     */
                    byte[] b = new byte[1024];
                    int a;
                    FileOutputStream fops = new FileOutputStream(f);
                    FileInputStream fis = new FileInputStream(file_resource);
                    while ((a = fis.read(b)) != -1) {
                        fops.write(b, 0, a);
                    }
                    fops.close();
                    fis.close();
                    qrcodeImg="/profile/avatar/"+photoName;
//                wechatID = QRCodeTools.deEncodeByPath(f.getAbsolutePath());
//                f.delete();
                    json.put("status",1);
                    json.put("qrcodeImg",qrcodeImg);
                }


//                json.put("userphoto",wechatID.replace("https://u.wechat.com/",""));


            }

        }else{
            json.put("status",2);
        }

        response.getWriter().write(json.toJSONString());


    }

    public  String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    /**
     *
     * http://sensors.ishansong.com:8106/r/js
     *
     * 端口号：6188
     * @param request
     * @param response
     * @throws IOException
     */

    @RequestMapping("/info/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String templateType=request.getParameter("templateType");
        String templateUrl=request.getParameter("templateUrl");
        String wechatID=request.getParameter("wechatID");
        templateUrl=templateUrl.substring(templateUrl.indexOf("ID=")+3);
        System.err.println(templateUrl);
        String fileName ="模板"+templateUrl+".zip";

//        File file_resource = ResourceUtils.getFile("classpath:static/h5/wangzhuan"+templateUrl);
        File file_resource = new File(h5_files_path+"/h5/wangzhuan"+templateUrl);
//        System.err.println(file_resource.getAbsolutePath());
        if(file_resource.isDirectory()){

            String indexText="_微信号修改后的首页.html";
            String indexText3="_qq号修改后的首页.html";
//            if("qq".equals(templateType)){
//                indexText="_qq号修改后的首页.html";
//            }
            StringBuilder sb=new StringBuilder();
            String index_html_name=file_resource.getAbsolutePath()+"/index"+templateUrl+".html";
            String index_html_name2=file_resource.getAbsolutePath()+"/index"+templateUrl+indexText;
            String index_html_name3=file_resource.getAbsolutePath()+"/index"+templateUrl+indexText3;

            File index_html=new File(index_html_name);
            try (FileReader reader = new FileReader(index_html);
                 BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
            ) {
                String line;
                //网友推荐更加简洁的写法
                while ((line = br.readLine()) != null) {
                    // 一次读入一行数据
//                    System.out.println(line);

                    if(line.contains("#测试微信号#")){
                        line=line.replace("#测试微信号#",wechatID);
                    }


                    if("qq".equals(templateType)){

                        if(line.contains("weixin://")){
                            line=line.replace("weixin://","mqq://");
                        }
                        if(line.contains("微信")){
                            line=line.replace("微信","qq");
                        }

                    }

                    sb.append(line+"\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            File writeName = new File(index_html_name2); // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName3 = new File(index_html_name3); // 相对路径，如果没有则要建立一个新的output.txt文件
            if(writeName.exists()){
                writeName.delete();
            }
            if(writeName3.exists()){
                writeName3.delete();
            }

            if("qq".equals(templateType)){
                writeName3.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            }else{
                writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            }


            try (FileWriter writer = new FileWriter("qq".equals(templateType)?writeName3:writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(sb.toString()); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }


            String downloadPath = Global.getDownloadPath();
            File file1=new File(downloadPath);
            if(!file1.exists()){
                file1.mkdirs();
            }
            String realPath = downloadPath+"/"+fileName;
            File file = new File(realPath);
            FileOutputStream fos1 = new FileOutputStream(file);
            ZipUtils.toZip(file_resource.getAbsolutePath(), fos1,true);


            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;

            try {

                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            InfoRecord record=new InfoRecord();
            record.setDataType("downloadTemplate");
            record.setMethod(request.getMethod());
            record.setRemoteAddr(request.getRemoteAddr());
            record.setUserAgent(request.getHeader("user-agent"));
            record.setRequestUri(request.getRequestURI());
            record.setUserId(ShiroUtils.getUserId().intValue());
            record.setCreateTime(new Date());
            record.setRemark(wechatID+","+templateUrl);
            iInfoRecordService.insertInfoRecord(record);

        }





    }

}
