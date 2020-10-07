package com.mci.designpattern.practice.apiauthentication;

public interface CredentialStorage {
    String getPasswordByAppId(String appId);
}
