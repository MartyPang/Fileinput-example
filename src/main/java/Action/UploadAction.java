package Action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Created by Marty Pang on 2017/2/17.
 */

public class UploadAction extends BaseAction{
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public void uploadFiles() throws Exception{
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        JSONObject jsonObject = new JSONObject();

       // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(getRequest())) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            jsonObject.put("msg", "error");
            out.write(jsonObject.toString());
            return;
        }


        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        System.out.println(uploadPath);
        //记录上传的文件名
        String loadFileName = null;


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(getRequest());
            System.out.println(formItems.size());
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段

                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();

                        loadFileName = fileName;

                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);

                        request.setAttribute("message", "upload successful!");
                    }else{
                        String name = item.getFieldName();
                        String value = new String(item.getString().getBytes("ISO8859_1"),"utf-8");
                        System.out.println(name + " " + value);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "error: " + ex.getMessage());
            jsonObject.put("msg", "error");
            out.write(jsonObject.toString());
            return;
        }
        jsonObject.put("msg", "ok");
        out.write(jsonObject.toString());
    }
}
