package com.unisca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.unisca.service.QRService;

@RestController
public class QRController {

    @Autowired
    private QRService qrService;

    @GetMapping(value = "/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQR(@RequestParam String texto) throws Exception {
        return qrService.generateQRCodeImage(texto, 300, 300);
    }
}
