package com.onlinelearning.util;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SystemUtil {

    public static String generate() {
        try {
            SystemInfo si = new SystemInfo();
            ComputerSystem computerSystem = si.getHardware().getComputerSystem();
            CentralProcessor processor = si.getHardware().getProcessor();

            // Lấy các thông số phần cứng
            String boardSerial = computerSystem.getBaseboard().getSerialNumber();
            String biosSerial = computerSystem.getSerialNumber();
            String cpuId = processor.getProcessorIdentifier().getProcessorID();

            String raw = String.format("%s|%s|%s", boardSerial, biosSerial, cpuId);

            // Kiểm tra nếu dữ liệu trống hoặc mặc định
            if (isInvalid(raw)) {
                raw = getFallbackFingerprint();
            }

            return hash(raw);
        } catch (Exception e) {
            return hash(getFallbackFingerprint());
        }
    }

    private static boolean isInvalid(String raw) {
        return raw == null || raw.isBlank() ||
                raw.contains("To be filled by O.E.M.") ||
                raw.contains("Default string");
    }

    private static String getFallbackFingerprint() {
        return String.format("%s|%s|%d",
                System.getProperty("user.name"),
                System.getProperty("os.name"),
                Runtime.getRuntime().availableProcessors());
    }

    private static String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
