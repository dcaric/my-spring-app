package com.infybuzz.service;
import com.infybuzz.entity.Student;
import com.infybuzz.entity.StudentTypes.StudentSimple;
import com.infybuzz.repository.StudentRepository;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


/**
 * @author dcaric on 10/04/2022
 * @project spring-boot-app
 */
@Service
public class StudentService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents() {
        System.out.println("FINDALL");
        return studentRepository.findAll();
    }

    public List<StudentSimple> getAllStudents2(String name) {
        return studentRepository.findByNameLike2(name);
    }

    public  Student getById(Long id) throws DocumentException, IOException {
        return studentRepository.findById(id).get();
    }

    public ResponseEntity<Resource> getPdf(String name) throws DocumentException, IOException {
        try {
            System.out.println("name: " + name);

            StudentService thymeleaf2Pdf = new StudentService();
            String html = thymeleaf2Pdf.parseThymeleafTemplate(name);
            System.out.println("html: " + html);
            String outputFile = thymeleaf2Pdf.generatePdfFromHtml(html);
            System.out.println("getPdf outputFile: " + outputFile);

            File file = new File(outputFile);

            Path path = Paths.get(file.getAbsolutePath());
            System.out.println("getPdf path: " + path);

            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            /*
            InputStreamResource resource = new InputStreamResource(new FileInputStream(outputFile));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type");
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "dario.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(outputFile.length())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
            */
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Disposition", "attachment; filename=" + name + ".pdf");

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);


            //return respEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return "Student has been deleted";
    }

    // PDF from Thymeleaf template demo ************************************************

    private String parseThymeleafTemplate(String name) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", name + ".pdf  FILE");

        return templateEngine.process("templates/thymeleaf_template", context);
    }

    public String generatePdfFromHtml(String html) throws IOException, DocumentException {
        String currentPath = new java.io.File(".").getCanonicalPath() + "/classes/templates/";
        System.out.println("currentPath: " + currentPath);
        String savedPath =  System.getProperty("user.dir");


        String outputFile = savedPath + "/thymeleaf.pdf";
        System.out.println("outputFile: " + outputFile);
        OutputStream outputStream = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        System.out.println("outputStream: " + outputStream);

        renderer.createPDF(outputStream);

        outputStream.close();

        return outputFile;
    }
    //************************************************

}
