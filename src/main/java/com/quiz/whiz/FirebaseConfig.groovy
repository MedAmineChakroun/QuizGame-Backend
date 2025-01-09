package com.quiz.whiz;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource;
import com.google.auth.oauth2.GoogleCredentials

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        System.out.println("Loading Firebase service account...");
        InputStream serviceAccount =
                new ClassPathResource("firebase-service-account.json").getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            System.out.println("Initializing FirebaseApp...");
            FirebaseApp.initializeApp(options);
        }

        System.out.println("FirebaseAuth bean created.");
        return FirebaseAuth.getInstance();
    }
}
