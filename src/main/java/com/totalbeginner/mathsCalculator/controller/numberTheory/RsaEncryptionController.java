package com.totalbeginner.mathsCalculator.controller.numberTheory;

import com.totalbeginner.mathsCalculator.dto.numberTheory.RsaEncryptionResult;
import com.totalbeginner.mathsCalculator.service.numberTheory.RsaEncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RsaEncryptionController {

    private final RsaEncryptionService rsaEncryptionService;

    public RsaEncryptionController(RsaEncryptionService rsaEncryptionService) {
        this.rsaEncryptionService = rsaEncryptionService;
    }

    @GetMapping("/rsa-encryption")
    public String showRsaWalkthrough(Model model) {
        model.addAttribute("result", new RsaEncryptionResult());
        return "numberTheory/rsaEncryption";
    }

    @PostMapping("/rsa-encryption")
    public String calculateRsaEncryptionWalkthrough(
        @RequestParam int p1,
        @RequestParam int p2,
        @RequestParam(required = false, defaultValue = "0") int e,
        @RequestParam(required = false) Integer message,
        @RequestParam(required = false) Integer ciphertext,

        @RequestParam(defaultValue = "1") int currentRsaSection,
        @RequestParam(required = false) String action,

        Model model
    ) {
        RsaEncryptionResult result =
                rsaEncryptionService.buildResult(p1, p2, e, message, ciphertext);

            if ("proceed-section-2".equals(action)) {
                currentRsaSection = 2;
            }
            if ("proceed-section-3".equals(action)) {
                currentRsaSection = 3;
            }
            if ("proceed-section-4".equals(action)) {
                currentRsaSection = 4;
            }
        result.setCurrentRsaSection(currentRsaSection);
        model.addAttribute("result", result);

        return "numberTheory/rsaEncryption";
    }
}