package lv.javaguru.java2.servlet;

/**
 * Created by Pavel on 10.11.2016..
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
@WebServlet(urlPatterns = { "/upload" })
public class FileUploadHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;







    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();



        // Check User has logged on
        Utils utils = new Utils();
        User loginedUser = utils.checkIfUserLoggedIn(request);
        String subdir = loginedUser.getUsername();


        final String UPLOAD_DIRECTORY = getServletContext().getInitParameter("upload-dir") + File.separator + subdir;

        File directory = new File(String.valueOf(UPLOAD_DIRECTORY));
        if (! directory.exists()){
            directory.mkdir();
        }

        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        //System.out.println(item.getContentType());
                        if (item.getContentType().startsWith("image/")) {
                            // It's an image.
                            String name = getServletContext().getInitParameter("avatar-name") + ".jpg"; //+ FilenameUtils.getExtension(new File(item.getName()).getName()); //new File(item.getName()).getName();
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        }
                        else {
                            out.println("2");
                            return;
                        }
                    }
                }

                //File uploaded successfully
                //request.setAttribute("message", "File Uploaded Successfully");
                out.println("1");
            } catch (Exception ex) {
                //request.setAttribute("message", "File Upload Failed due to " + ex);
                out.println("File Upload Failed due to " + ex);
            }

        }else{
            //request.setAttribute("message",
                    //"Sorry this Servlet only handles file upload request");
            out.println("Sorry this Servlet only handles file upload request");
        }


        //request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

}

