package com.eurotech.tests.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerExample {
    // Logger nesnesi oluştur
    private static final Logger logger = LoggerFactory.getLogger(LoggerExample.class);

    public static void main(String[] args) {
        logger.info("Uygulama başladı");          // Bilgi mesajı
        logger.debug("Debug mesajı (detaylar)");  // Debug mesajı
        logger.warn("Dikkat! Küçük bir uyarı");   // Uyarı mesajı
        logger.error("Bir hata oluştu!");         // Hata mesajı

        System.out.println("Normal System.out.println da çalışır");
    }
}
