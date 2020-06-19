package com.lethanh98.controller;

import com.lethanh98.annotation.ApiResponsesBase;
import com.lethanh98.reponse.DecryptResponse;
import com.lethanh98.reponse.EncryptResponseDto;
import com.lethanh98.reponse.VaultSignResponseData;
import com.lethanh98.request.DecryptRequestDto;
import com.lethanh98.request.EncryptRequestDto;
import com.lethanh98.request.VaultSignRequestData;
import com.lethanh98.request.VerifyRequestDto;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Plaintext;
import org.springframework.vault.support.Signature;
import org.springframework.vault.support.SignatureValidation;
import org.springframework.vault.support.VaultSignatureVerificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/vault")
@ApiResponsesBase()
public class VaultTest {
    private static String NameTransit = "test-key";
    private static String NameTransit_sigkey = "sigkey";
    private final transient VaultOperations vaultOps;

    public VaultTest(VaultOperations vaultOps) {
        this.vaultOps = vaultOps;
    }

    @PostMapping("/encrypt")
    public EncryptResponseDto encrypt(@RequestBody EncryptRequestDto encryptRequestDto) {
        String dataEncrypt = this.vaultOps.opsForTransit().encrypt(NameTransit, encryptRequestDto.getData());
        EncryptResponseDto encryptResponseDto = new EncryptResponseDto();
        encryptResponseDto.setData(encryptRequestDto.getData());
        encryptResponseDto.setCipherText(dataEncrypt);
        return encryptResponseDto;
    }

    @PostMapping("/decrypt")
    public DecryptResponse decrypt(@RequestBody DecryptRequestDto decryptRequestDto) {
        String dataDecrypt = this.vaultOps.opsForTransit().decrypt(NameTransit, decryptRequestDto.getCipherText());
        DecryptResponse decryptResponse = new DecryptResponse();
        decryptResponse.setData(dataDecrypt);
        return decryptResponse;
    }

    @PostMapping("/sign")
    public VaultSignResponseData signData(@RequestBody VaultSignRequestData encryptRequestDto) {
        Signature datasign = this.vaultOps.opsForTransit().sign(NameTransit_sigkey, Plaintext.of(encryptRequestDto.getInput()));
        VaultSignResponseData encryptResponseDto = new VaultSignResponseData();
        encryptResponseDto.setInput(encryptRequestDto.getInput());
        encryptResponseDto.setSignature(datasign.getSignature());
        return encryptResponseDto;
    }

    @PostMapping("/verify")
    public SignatureValidation verifyData(@RequestBody VerifyRequestDto verifyRequestDto) {
        VaultSignatureVerificationRequest vaultSignatureVerificationRequest =
                VaultSignatureVerificationRequest.create(Plaintext.of(verifyRequestDto.getInput()), Signature.of(verifyRequestDto.getSignature()));
        return vaultOps.opsForTransit().verify(NameTransit_sigkey, vaultSignatureVerificationRequest);
    }


}
