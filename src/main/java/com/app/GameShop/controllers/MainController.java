package com.app.GameShop.controllers;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class MainController {

    private String websitePathStr = "web/index.html";
    private String stylePathStr = "web/style/main.css";
    private String scriptsPathStr = "web/scripts/main.js";

    @GetMapping("/")
    public String mainPage() {
        Path websitePath = Path.of(websitePathStr);
        Path stylePath = Path.of(stylePathStr);
        Path scriptsPath = Path.of(scriptsPathStr);
        String contentHTML = null, contentCSS = null, contentJS = null;
        try {
            contentHTML = Files.readString(websitePath);
            contentCSS = Files.readString(stylePath);
            contentJS = Files.readString(scriptsPath);
        } catch (IOException e) {
            return "Can not open files : " +
                    websitePathStr + " : " +
                    stylePathStr + " : " +
                    scriptsPathStr;
        }

        return contentHTML
                .replace("<!--<$USE_CSS$>-->", "\n<style>\n" + contentCSS + "\n</style>\n")
                .replace("<!--<$USE_JS$>-->", "\n<script>\n" + contentJS + "\n</script>\n");
    }

}
