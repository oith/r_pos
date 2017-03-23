package org.reflection.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.reflection.exception.ObjectNotFoundException;
import org.reflection.model.auth.AuthUser;
import org.reflection.model.com.AdmCodeDef;
import org.reflection.model.com.AdmMenuItem;
import org.reflection.service.AdmCodeDefService;
import org.reflection.service.AdmMenuItemService;
import org.reflection.service.auth.AuthUserExt;
import org.reflection.service.auth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes({"selectAny"})
public abstract class _BaseController {

    protected static final String FLASH_ERROR = "flashError";
    protected static final String FLASH_WARNING = "flashWarning";
    protected static final String FLASH_SUCCESS = "flashSuccess";
    protected static final String FLASH_MESSAGE = "flashMessage";

    protected static final String SEARCH_CRITERIA = "searchCriteria";

    protected static final String ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND = "default.error.message.object.not.found";
    protected static final String ERROR_MESSAGE_DATA_INTEGRITY_VIOLATION = "default.error.message.data.integrity.violation";

    protected static final String FEEDBACK_MESSAGE_KEY_CREATED = "default.feedback.message.created";
    protected static final String FEEDBACK_MESSAGE_KEY_DELETED = "default.feedback.message.deleted";
    protected static final String FEEDBACK_MESSAGE_KEY_EDITED = "default.feedback.message.edited";
    protected static final String FEEDBACK_MESSAGE_KEY_COPIED = "default.feedback.message.copied";

    protected static final Map<String, String> MENU_CODE_MAP = new TreeMap();
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    protected MessageSource messageSource;
    @Autowired
    private AdmMenuItemService admMenuItemService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AdmCodeDefService admCodeDefService;

    @PostConstruct
    public void init() {
        for (AdmMenuItem admMenuItem : admMenuItemService.findAll()) {
            MENU_CODE_MAP.put(admMenuItem.getUrlPath(), admMenuItem.getCode().toUpperCase());
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new _DatePropertyEditor());
    }

    protected AuthUser currAuthUser() {
        try {
            AuthUserExt authUserExt = (AuthUserExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return authUserService.findByUsername(authUserExt.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    protected synchronized String genCode(String pojoClass) {
        return genCode(pojoClass, null, null, null);
    }

    protected synchronized String genCode(String pojoClass, String startWith) {
        return genCode(pojoClass, startWith, null, null);
    }

    protected synchronized String genCode(String pojoClass, String startWith, Integer length) {
        return genCode(pojoClass, startWith, length, null);
    }

    protected synchronized String genCode(String pojoClass, String startWith, Integer length, String endWith) {

        AdmCodeDef allCodeDef = null;
        try {
            allCodeDef = admCodeDefService.findByPojoClass(pojoClass);
        } catch (Exception e) {
        }

        if (allCodeDef == null) {
            AdmCodeDef allCodeDefx = new AdmCodeDef(pojoClass, startWith, length, endWith);
            allCodeDefx.setFullName(pojoClass);
            allCodeDef = admCodeDefService.create(allCodeDefx);
        }

        String lastCodeVal = allCodeDef.getNextValue() + "";

        if (allCodeDef.getCodeLength() != null) {
            while (lastCodeVal.length() < allCodeDef.getCodeLength()) {
                lastCodeVal = "0" + lastCodeVal;
            }
        }

        if (allCodeDef.getStartWith() != null) {
            lastCodeVal = allCodeDef.getStartWith() + lastCodeVal;
        }
        if (allCodeDef.getEndWith() != null) {
            lastCodeVal = lastCodeVal + allCodeDef.getEndWith();
        }

        allCodeDef.setNextValue(allCodeDef.getNextValue() + 1);
        try {
            admCodeDefService.update(allCodeDef);
        } catch (Exception e) {
            System.out.println("err mac must chk code gen (): " + e);
        }

        return lastCodeVal;
    }

    public void errorHandler(BindingResult bindingResult, Exception e) {

        System.out.println("err in binding 1123 mac: " + e);
        System.out.println("err in binding oo 1123 mac: " + bindingResult.getObjectName());

        System.out.println("ddddddddddd0" + e.getCause());
        System.out.println("ddddddddddd1" + e.getCause().getCause());
        System.out.println("ddddddddddd2" + e.getCause().getCause().getCause());
        //e.printStackTrace();

        if (e instanceof DuplicateKeyException) {
            DuplicateKeyException uuu = (DuplicateKeyException) e;

            String hhh = uuu.getCause().getMessage();

            System.out.println("err dup key: " + hhh);

            int kk = hhh.lastIndexOf(": \"");
            if (kk != -1) {
                hhh = hhh.substring(kk + 3, hhh.length() - 4);
            }

            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Duplicate record notification for value '" + hhh + "'");

            bindingResult.addError(yyyy);
        } else if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
            DataIntegrityViolationException fff = (DataIntegrityViolationException) e;
            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Data Integrity Violation '" + fff.getMessage() + "'");
            //FieldError yyyy=   new FieldError(bindingResult.getObjectName(), "code", e.getMessage()+" real val: "+ uuu.getRootCause()+" hhhh"+ val);

            bindingResult.addError(yyyy);
        } else {

            e.printStackTrace();

            System.out.println("errr 11165: " + e);
            ObjectError yyyy = new ObjectError(bindingResult.getObjectName(), "Info: " + e.getMessage() + "");
            bindingResult.addError(yyyy);
        }
    }

    protected void addFeedbackMessage(RedirectAttributes redirectAttributes, String flashMessageType, String code, Object... params) {
        Locale current = LocaleContextHolder.getLocale();

        String codex = code;
        String localizedFeedbackMessage;

        if (params != null && params[0] != null) {
            if (params[0] instanceof ObjectNotFoundException) {
                codex = ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND;
                localizedFeedbackMessage = messageSource.getMessage(codex, params, current);
            } else if (params[0] instanceof org.springframework.dao.DataIntegrityViolationException) {
                codex = ERROR_MESSAGE_DATA_INTEGRITY_VIOLATION;
                localizedFeedbackMessage = messageSource.getMessage(codex, params, current);
            } else {
                localizedFeedbackMessage = messageSource.getMessage(codex, params, current);
            }
        } else {
            localizedFeedbackMessage = messageSource.getMessage(codex, params, current);
        }

        redirectAttributes.addFlashAttribute(flashMessageType, localizedFeedbackMessage);
    }

    @GetMapping("/getFile/{code}.{ext}")
    public
    @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable("code") String code, @PathVariable("ext") String ext, HttpServletRequest request) {
        System.out.println("finding getFile: code: " + code + " ext: " + ext);
        return getFile(request, "files", code, ext);
    }

    @GetMapping("/getPhotoOriginal/{code}.{format}")
    public
    @ResponseBody
    ResponseEntity<byte[]> getPhotoOriginal(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {
        System.out.println("finding getPhotoOriginal: code: " + code + " format: " + format);
        return getImage(request, "pics_original", code, format);
    }

    @GetMapping("/getPhotoMedium/{code}.{format}")
    public
    @ResponseBody
    ResponseEntity<byte[]> getPhotoMedium(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {
        System.out.println("finding getPhotoMedium: code: " + code + " format: " + format);
        return getImage(request, "pics_medium", code, format);
    }

    @GetMapping("/getPhotoSmall/{code}.{format}")
    public
    @ResponseBody
    ResponseEntity<byte[]> getPhotoSmall(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {
        System.out.println("finding getPhotoSmall: code: " + code + " format: " + format);
        return getImage(request, "pics_small", code, format);
    }

    ResponseEntity<byte[]> getFile(HttpServletRequest request, String dir, String code, String ext) {
        try {
            File parent = getOuterParentPath(request);
            File file = new File(parent, "repositories" + File.separator + dir + File.separator + code + "." + ext);

            byte[] buf = IOUtils.toByteArray(new FileInputStream(file));
            System.out.println("size file: " + buf.length);
            return new ResponseEntity<>(buf, HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }

    ResponseEntity<byte[]> getImage(HttpServletRequest request, String dir, String code, String format) {
        try {
            File parent = getOuterParentPath(request);
            File file = new File(parent, "repositories" + File.separator + dir + File.separator + code + "." + format);
            final HttpHeaders headers = new HttpHeaders();
            if (file.exists()) {
                byte[] buf = IOUtils.toByteArray(new FileInputStream(file));
                System.out.println("size file: " + buf.length);

                if (format.toLowerCase().contains("jpg")) {
                    headers.setContentType(MediaType.IMAGE_JPEG);
                } else if (format.toLowerCase().contains("gif")) {
                    headers.setContentType(MediaType.IMAGE_GIF);
                } else if (format.toLowerCase().contains("png")) {
                    headers.setContentType(MediaType.IMAGE_PNG);
                }
                return new ResponseEntity<>(buf, headers, HttpStatus.CREATED);
            } else {//image not found
                String ls = dir.equals("pics_small") ? "list_" : "show_";
                File root = new File(request.getServletContext().getRealPath("/"));
                byte[] buf = IOUtils.toByteArray(new FileInputStream(root + File.separator + "resources" + File.separator + "images" + File.separator + ls + "no_pic_other.png"));
                headers.setContentType(MediaType.IMAGE_PNG);
                return new ResponseEntity<>(buf, headers, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println("err getimage : " + e);
            return null;
        }
    }

    protected String multipartFileHandler(MultipartHttpServletRequest request, String col) {

        MultipartFile multipartFile = request.getFile(col + "OBJ");

        if ((multipartFile != null && multipartFile.getSize() > 0)) {

            try {
                String fileCaption = multipartFile.getOriginalFilename();

                File parent = getOuterParentPath(request);
                File file = new File(parent,
                        "repositories"
                                + File.separator
                                + "files"
                                + File.separator
                                + fileCaption);

                FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
                System.out.println("Go to the location:  " + file.toString() + " on your computer has been stored.");
                // currObject.setPicFile("/pics/" + fileCaption);
                //return "/" + dir + "/" + fileCaption;
                return fileCaption;
            } catch (Exception e) {
                System.out.println("set " + col + " err: " + e);
                //currObject.setPicFile("err: " + e);
                return "err: " + e;
            }
        }
        return "";
    }

    protected String multipartImageFileHandler(MultipartHttpServletRequest request, String col) {

        System.out.println("hhhhhhhhhhh 1025:" + col);
        //MultipartFile picFileOBJ = request.getFile("picFileOBJ");
        MultipartFile multipartFile = request.getFile(col + "OBJ");

        if (multipartFile != null && multipartFile.getSize() > 0) {

            try {
                //String fileCaption = multipartFile.getOriginalFilename();

                String jjj = multipartFile.getContentType();
                System.out.println("content type : " + jjj);

                String extx = "jpg";

                if (jjj.contains("jpeg")) {
                    extx = "jpg";
                } else if (jjj.contains("png")) {
                    extx = "png";
                } else if (jjj.contains("gif")) {
                    extx = "gif";
                }

                String idOne = UUID.randomUUID().toString();
                String fileCaption = idOne + "." + extx;
                File parent = getOuterParentPath(request);

                File filePicsOriginal = new File(parent
                        ,
                        "repositories"
                                + File.separator
                                + "pics_original"
                                + File.separator
                                + fileCaption);
                if (!filePicsOriginal.getParentFile().exists()) {
                    filePicsOriginal.getParentFile().mkdirs();
                }

                File filePicsMedium = new File(parent
                        , "repositories"
                        + File.separator
                        + "pics_medium"
                        + File.separator
                        + fileCaption);
                if (!filePicsMedium.getParentFile().exists()) {
                    filePicsMedium.getParentFile().mkdirs();
                }

                File filePicsSmall = new File(parent
                        , "repositories"
                        + File.separator
                        + "pics_small"
                        + File.separator
                        + fileCaption);

                if (!filePicsSmall.getParentFile().exists()) {
                    filePicsSmall.getParentFile().mkdirs();
                }

                byte[] buff = multipartFile.getBytes();
                FileUtils.writeByteArrayToFile(filePicsOriginal, buff);

                picSizer(multipartFile.getInputStream(), extx, filePicsMedium, 120);
                picSizer(multipartFile.getInputStream(), extx, filePicsSmall, 60);

                //FileUtils.writeByteArrayToFile(filet, multipartFile.getBytes());
                System.out.println("Go to the location:  " + filePicsOriginal.toString() + " on your computer and verify that the has been stored.");
                // currObject.setPicFile("/pics/" + fileCaption);
                //return "/" + dir + "/" + fileCaption;
                return fileCaption;
            } catch (Exception e) {
                System.out.println("set " + col + " err: " + e);
                //currObject.setPicFile("err: " + e);
                return "err: " + e;
            }
        }
        return "";
    }

    void picSizer(InputStream inputStream, String extx, File filePicsMedium, int size) throws IOException {
        BufferedImage bsrc = ImageIO.read(inputStream);//  new File(data));
        BufferedImage bdest = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bdest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double) size / bsrc.getWidth(),
                (double) size / bsrc.getHeight());
        g.drawRenderedImage(bsrc, at);

        ImageIO.write(bdest, extx, filePicsMedium);
    }

    protected File getOuterParentPath(HttpServletRequest request) {
        File root = new File(request.getServletContext().getRealPath("/"));
        File rootUp = new File(root.getParentFile(), root.getName() + "_repo");
        return rootUp;
    }

    protected BigInteger faker(BigInteger id) {
//        try {
//            return new BigInteger(RequestContextHolder.currentRequestAttributes().getSessionId(), 16).xor(id);
//        } catch (Exception e) {
//            return id;
//        }
        return id;
    }
}
