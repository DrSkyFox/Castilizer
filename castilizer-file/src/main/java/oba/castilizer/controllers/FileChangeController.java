package oba.castilizer.controllers;

import lombok.extern.slf4j.Slf4j;
import oba.castilizer.model.ChangeSet;
import oba.castilizer.service.IFileChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileChangeController {

    private final IFileChange service;

    @Autowired
    public FileChangeController(IFileChange service) {
        this.service = service;
    }

    @PostMapping("/change")
    public String fileChangeParam(@RequestBody ChangeSet changeSet) {
        log.info(changeSet.toString());
        return service.changeFileParam(changeSet.getParamName(), changeSet.getParamSet()).toString();
    }

}
