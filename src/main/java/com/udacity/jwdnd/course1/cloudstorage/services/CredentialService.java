package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Service for the Credential table.
 */
@Service
public class CredentialService {

    private EncryptionService encryptionService;

    private CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public List<String> decryptPasswords() {
        List<Credential> credentials = this.credentialMapper.getAllCredentials();
        List<String> credentialsEncrypted = new ArrayList<>();
        for (Credential credential : credentials) {
            credentialsEncrypted.add(this.encryptionService.decryptValue(credential.getUsername(), credential.getKey()));
        }
        return credentialsEncrypted;
    }

    public int addCredential(Credential credential, Integer userid) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedValue = this.encryptionService.encryptValue(credential.getPassword(), encodedKey);
        return this.credentialMapper.insertCredential(new Credential(null, credential.getUrl(), credential.getUsername(), encodedKey, encryptedValue, userid));
    }

    public List<Credential> getAllCredentials() {
        return this.credentialMapper.getAllCredentials();
    }

    public boolean isCredentialAlreadyAvailable(Integer credentialid) {
        return this.credentialMapper.getCredential(credentialid) != null;
    }

    public int updateCredential(Credential credential, Integer userid) {
        int deletedRows = this.deleteCredential(credential.getCredentialid());
        if (deletedRows < 0) {
            return 0;
        } else {
            int addedRows = addCredential(credential, userid);
            return addedRows;
        }
    }

    public Credential getCredential(Integer credentialid) {
        return this.credentialMapper.getCredential(credentialid);
    }

    public int deleteCredential(Integer credentialid) {
        return this.credentialMapper.deleteCredential(credentialid);
    }
}
