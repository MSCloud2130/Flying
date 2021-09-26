package com.flying.seeker.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.seeker.Users.Client;
import com.flying.seeker.Users.ClientRepository;
import com.flying.seeker.Users.Seller;
import com.flying.seeker.Users.SellerRepository;
import com.flying.seeker.product.Product;
import com.flying.seeker.product.ProductRepository;
import com.flying.seeker.review.Review;
import com.flying.seeker.review.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements ApplicationRunner {

        @Autowired
        ProductRepository productRepository;

        @Autowired
        SellerRepository sellerRepository;

        @Autowired
        ClientRepository clientRepository;

        @Autowired
        ReviewRepository reviewRepository;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                productRepository.deleteAll();
                sellerRepository.deleteAll();
                clientRepository.deleteAll();
                reviewRepository.deleteAll();

                List<String> tokens = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                        String token = "token" + String.valueOf(i);
                        tokens.add(token);
                }
                Client c1 = new Client("Juan Manuel", 32, "email@gmail.com", "img.com", "=3dad!23w", tokens.get(0),
                                5000000.00);
                Client c2 = new Client("Diego Arroyo", 25, "email2@gmail.com", "img.com", "=3dad!23w", tokens.get(1),
                                200000.00);
                Client c3 = new Client("Nelson Mosquera", 23, "email3@gmail.com", "img.com", "=3dad!23w", tokens.get(2),
                                10000000.00);
                Client c4 = new Client("Juan Carlos", 45, "emai4l@gmail.com", "img.com", "=3dad!23w", tokens.get(3),
                                0.0);
                clientRepository.insert(c1);
                clientRepository.insert(c2);
                clientRepository.insert(c3);
                clientRepository.insert(c4);

                Seller s1 = new Seller("David Suarez", 52, "300-558-4785", "www.social2.com", "email5@hotmail.com",
                                "password", tokens.get(4), "img.com", 500000.00);
                Seller s2 = new Seller("Manuel Munoz", 40, "300-258-7894", "www.social3.com", "email6@hotmail.com",
                                "password", tokens.get(5), "img.com", 500000.00);
                Seller s3 = new Seller("Sara Martinez", 25, "302-236-5874", "www.social4.com", "email7@hotmail.com",
                                "password", tokens.get(6), "img.com", 500000.00);

                sellerRepository.insert(s1);
                sellerRepository.insert(s2);
                sellerRepository.insert(s3);

                Date date = new Date();
                Instant instant = Instant.now();

                Product p1 = new Product("EcoAdventure", "Avion", "Hotel", "Comida vegetariana", 500000.00,
                                date.from(instant), "EcoAdventure es una aventura ecologica que desearas repetir!",
                                false, "BOG", "MED", "Img.com",
                                List.of("descanso", "diversion", "extremo", "aventura"));

                Product p2 = new Product("EcoLove", "Bus", "Hostel", "Comida colombiana", 400000.00, date.from(instant),
                                "EcoLove es una aventura que podras disfrutar con tu pareja!", true, "BOG", "MIA",
                                "Img.com", List.of("romance", "diversion"));

                Product p3 = new Product("EcoFamily", "Bus", "Hotel", "Comida tipica", 580000.00, date.from(instant),
                                "EcoFamily es una experiencia que tu familia te agradecera!", false, "BOG", "MOS",
                                "Img.com", List.of("familia", "diversion", "tranquilidad"));

                Product p4 = new Product("EcoParque", "Avion", "CABAÑA", "Comida tropical", 400000.00,
                                date.from(instant), "EcoParque es un parque ecologico el cual querras visitar!", false,
                                "MED", "SAI", "Img.com", List.of("parque", "diversion", "familia"));

                Product p5 = new Product("EcoDeluxe", "Avion", "Hotel", "Comida tipica alemana", 50000000.00,
                                date.from(instant),
                                "EcoDeluxe es un servicio exclusivo para personas que desean viajar a los lugares mas remotos!",
                                false, "MIA", "GER", "Img.com", List.of("exclusivo", "continental"));

                Product p6 = new Product("EcoInfo", "Bus", "Hostel", "Comida colombiana", 50000.00, date.from(instant),
                                "EcoInfo es un servicio para aprender mas sobre la ecologia!", true, "BOG", "MED",
                                "Img.com", List.of("aprendizaje", "familia"));

                Product p7 = new Product("EcoDinner", "Avion", "Hotel", "Comida vegetariana", 500000.00,
                                date.from(instant),
                                "EcoDinner es un servicio el cual te brinda una cena en un restaurante ecologico",
                                false, "BOG", "MED", "Img.com", List.of("descanso", "cena", "familia"));

                productRepository.insert(p1);
                productRepository.insert(p2);
                productRepository.insert(p3);
                productRepository.insert(p4);
                productRepository.insert(p5);
                productRepository.insert(p6);
                productRepository.insert(p7);

                s1.addProduct(p1);
                s1.addProduct(p4);
                s1.addProduct(p6);
                s2.addProduct(p2);
                s2.addProduct(p5);
                s2.addProduct(p7);
                s3.addProduct(p3);

                c1.addProduct(p1);
                c1.addProduct(p2);
                c2.addProduct(p2);
                c3.addProduct(p3);
                c3.addProduct(p1);

                sellerRepository.save(s1);
                sellerRepository.save(s2);
                sellerRepository.save(s3);

                clientRepository.save(c1);
                clientRepository.save(c2);
                clientRepository.save(c3);
                clientRepository.save(c4);

                Review r1 = new Review("Best place ever!!", 5);
                Review r2 = new Review("Odie el lugar!!!", 1);
                Review r3 = new Review("Puede Mejorar!!", 5);

                c1.addReview(r1);
                c2.addReview(r2);
                c3.addReview(r3);

                p1.getReview().add(r1);
                p2.getReview().add(r2);
                p3.getReview().add(r3);

                reviewRepository.insert(r1);
                reviewRepository.insert(r2);
                reviewRepository.insert(r3);

                clientRepository.save(c1);
                clientRepository.save(c2);
                clientRepository.save(c3);
                productRepository.save(p1);
                productRepository.save(p2);
                productRepository.save(p3);
        }

}
